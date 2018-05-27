import java.util.LinkedList;

class Path {
    public String simplifyPath(String path) {
        if(path.matches("[/.]+")) {
            return "/";
        }
        else if(path.isEmpty())
            return "";
        LinkedList<String> pathList = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int dot = 0;
        for(int i=0;i<path.length();i++){

            if(path.charAt(i)=='/') {
                if(dot==2 && !pathList.isEmpty())
                    pathList.removeLast();
                if(sb.length()>0) {
                    pathList.add(sb.toString());
                    sb.delete(0, sb.length());
                }

                dot=0;
            }
            else if(path.charAt(i)=='.') {
                dot++;
            }
            else
                sb.append(path.charAt(i));



        }
        if(sb.length()>0)
            pathList.add(sb.toString());
        if(pathList.isEmpty())
            return "";

        sb = new StringBuilder();
        for(String pathstr:pathList){
            sb.append("/"+pathstr);
        }
        return sb.toString();
    }

    public static void main(String[] args){
        Path p = new Path();
        String str =p.simplifyPath("/.");
        System.out.println(str);
    }
}