import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

/**
 * Created by rupalh on 3/30/18.
 */
enum Status {
    READY, DONE, PARTIAL;
}

class Task {
    String name;
    int priority = 0;
    Status state;
    List<Task> depTasks = new ArrayList<>();

    public Task(String n, int pr) {

        this.name = n;
        this.priority = pr;

    }

    public void addTasks(List<Task> deps) {
        depTasks = deps;
    }
}

public class Dependency {
    LinkedList<Task> taskQueue = new LinkedList<>();

    Map<String, String[]> dependencies = new HashMap<>();

    public List<Task> getList(String[] tasks) {
        LinkedList<Task> list = new LinkedList<>();
        for (String s : tasks) {
            list.add(new Task(s, 0));
        }
        return list;
    }

    public void addTask(String n, int pri, String[] dep) {
        Task t = new Task(n, pri);


        taskQueue.add(t);
        dependencies.put(n, dep);

    }

    public void buildGraph() {
        for (Task task : taskQueue) {
            if (dependencies.get(task.name) != null) {
                List<Task> list = new ArrayList<>();
                for (String dep : dependencies.get(task.name)) {
                    for (Task task1 : taskQueue) {
                        if (task1.name.equals(dep))
                            list.add(task1);
                    }
                }
                task.addTasks(list);
            }
        }
    }

    /**
     * a->b,e
     * a->e
     * c
     * d
     */
    public void run() {
        taskQueue.sort((Task t1, Task t2) -> t2.priority - t1.priority);
        buildGraph();
        int i = 0;
        while (!taskQueue.isEmpty() && i < taskQueue.size()) {
            Task task = taskQueue.get(i);
//            System.out.println("checking.."+task.name);
            task.state = Status.DONE;

            for (Task dep : task.depTasks) {
                if (dep.state != Status.DONE) {
                    task.state = Status.PARTIAL;
                    i++;
                    break;
                }

            }
            if (task.state==Status.DONE) {
                taskQueue.remove(task);
                System.out.println(task.name);
                i = 0;
            }
        }
    }


    private void setState(String taskName, Status state) {
        for (Task task : taskQueue)
            if (task.name.equals(taskName))
                task.state = state;
    }

    public static void main(String[] args) {
        Dependency dep = new Dependency();
        dep.addTask("a", 10, new String[]{"b", "e"});
        dep.addTask("b", 5, new String[]{});
        dep.addTask("c", 7, new String[]{"d"});
        dep.addTask("d", 1, new String[]{});
        dep.addTask("e", 2, new String[]{});

        dep.run();

    }
}
