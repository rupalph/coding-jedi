'use strict';

const https = require('https');
const AWS = require('aws-sdk');

const EMAIL = 'rupalph@gmail.com';
const SNS = new AWS.SNS({ apiVersion: '2010-03-31' });


function findExistingSubscription(topicArn, nextToken, cb) {
    const params = {
        TopicArn: topicArn,
        NextToken: nextToken || null,
    };
    SNS.listSubscriptionsByTopic(params, (err, data) => {
        if (err) {
            console.log('Error listing subscriptions.', err);
            return cb(err);
        }
        const subscription = data.Subscriptions.filter((sub) => sub.Protocol === 'email' && sub.Endpoint === EMAIL)[0];
        if (!subscription) {
            if (!data.NextToken) {
                cb(null, null); // indicate that no subscription was found
            } else {
                findExistingSubscription(topicArn, data.NextToken, cb); // iterate over next token
            }
        } else {
            cb(null, subscription); // a subscription was found
        }
    });
}

/**
 * Subscribe the specified EMAIL to a topic.
 */
function createSubscription(topicArn, cb) {
    // check to see if a subscription already exists
    findExistingSubscription(topicArn, null, (err, res) => {
        if (err) {
            console.log('Error finding existing subscription.', err);
            return cb(err);
        }
        if (!res) {
            // no subscription, create one
            const params = {
                Protocol: 'email',
                TopicArn: topicArn,
                Endpoint: EMAIL,
            };
            SNS.subscribe(params, (subscribeErr) => {
                if (subscribeErr) {
                    console.log('Error setting up email subscription.', subscribeErr);
                    return cb(subscribeErr);
                }
                // subscription complete
                console.log(`Subscribed ${EMAIL} to ${topicArn}.`);
                cb(null, topicArn);
            });
        } else {
            // subscription already exists, continue
            cb(null, topicArn);
        }
    });
}

/**
 * Create a topic.
 */
function createTopic(topicName, cb) {
    SNS.createTopic({ Name: topicName }, (err, data) => {
        if (err) {
            console.log('Creating topic failed.', err);
            return cb(err);
        }
        const topicArn = data.TopicArn;
        console.log(`Created topic: ${topicArn}`);
        console.log('Creating subscriptions.');
        createSubscription(topicArn, (subscribeErr) => {
            if (subscribeErr) {
                return cb(subscribeErr);
            }
            // everything is good
            console.log('Topic setup complete.');
            cb(null, topicArn);
        });
    });
}

function pad(pad, str, padLeft) {
  if (typeof str === 'undefined')
    return pad;
  if (padLeft) {
    return (pad + str).slice(-pad.length);
  } else {
    return (str + pad).substring(0, pad.length);
  }
}

function slice(obj, start, end) {

    var sliced = {};
    var i = 0;
    for (var k in obj) {
        if (i >= start && i < end)
            sliced[k] = obj[k];

        i++;
    }

    return sliced;
}


/**
 * Pass the data to send as `event.data`, and the request options as
 * `event.options`. For more information see the HTTPS module documentation
 * at https://nodejs.org/api/https.html.
 *
 * Will succeed with the response body.
 */
exports.handler = (event, context, callback) => {
    var url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22ACOM%22%2C%22AAPL%22%2C%22GOOG%22%2C%22MSFT%22%2C%22IRBT%22%2C%22BJRI%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    https.get(url, function (res) {
        var noaaResponseString = '';
        console.log('Status Code: ' + res.statusCode);

        if (res.statusCode != 200) {
            console.log("Error")
        }

        res.on('data', function (data) {
            noaaResponseString += data;
        });

        res.on('end', function () {
            var response = JSON.parse(noaaResponseString);
            //console.log(response.query.results.quote)
            var stocks = response.query.results.quote
            var stockprices = JSON.stringify(response.query.results.quote)
            var headers = []
            var values = "\n"
            var padding = Array(15).join(' ') // make a string of 255 spaces
            for (var i = 0; i < 1 ; i++) {
                var len=Object.keys(stocks[i]).length

                for(var key in slice(stocks[i],0,len-4)) {

                      //values +=  key + "\t"
                        values += pad(padding,key.slice(0,5),false)
                      //values += sprintf("%20s", key)

                }
                values += "\n"
            }
            for ( i = 0; i < stocks.length; i++) {
                for( key in slice(stocks[i],0,len-4)) {

                      //values += stocks[i][key] + "\t"
                      values += pad(padding,stocks[i][key],false)
                      //values += sprintf("%20s", stocks[i][key])
                      console.log(key + ":" + stocks[i][key])
                }
                values += "\n"
            }

            console.log("Last key value:" + values)
            // create/get topic
            createTopic('aws-stock-sns-topic', (err, topicArn) => {
                if (err) {
                    return callback(err);
                }
                console.log(`Publishing to topic ${topicArn}`);
                // publish message
                const params = {
                    Message: `Stock prices today -- processed by Lambda ${values} `,
                    Subject: `Stock prices from your AWS Lambda`,
                    TopicArn: topicArn,
                };
                // result will go to function callback
                SNS.publish(params, callback);
            });
        });
    }).on('error', function (e) {
        console.log("Communications error: " + e.message);
    });

};
