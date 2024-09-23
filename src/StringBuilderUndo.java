import java.util.Stack;


public class StringBuilderUndo {

    private StringBuilder stringBuilder;
    private final int bufferSize = 5;

    private interface Action{
        void undo();
    }

    private class DeleteAction implements Action {
        private int size;
        public DeleteAction(int size) {
            this.size = size;
        }
        public void undo() {
            stringBuilder.delete(stringBuilder.length() - size, stringBuilder.length());
        }
    }

    private Stack<Action> actions = new Stack<>();
    public StringBuilderUndo() {
        stringBuilder = new StringBuilder();
    }

    public StringBuilderUndo reverse() {
        stringBuilder.reverse();
        Action action = new Action() {
            public void undo() {
                stringBuilder.reverse();
            }
        };
        if (actions.size() > bufferSize) {actions.remove(0);}
        actions.add(action);
        return this;
    }

    public StringBuilderUndo append(String str) {
        stringBuilder.append(str);

        Action action = new Action() {
            public void undo() {
                stringBuilder.delete(stringBuilder.length()-str.length(),stringBuilder.length());
            };
        };
        if (actions.size() > bufferSize) {actions.remove(0);}
        actions.add(action);
        return this;
    }

    public StringBuilderUndo insetrt(int len, String str) {
        stringBuilder.insert(len, str);

        Action action = new Action() {
            public void undo() {
                stringBuilder.delete(len,len + str.length());
            }
        };
        if (actions.size() > bufferSize) {actions.remove(0);}
        actions.add(action);
        return this;
    }

    public StringBuilderUndo replace(int start, int end, String str) {
        String strOld = stringBuilder.substring(start, end);
        stringBuilder.replace(start, end, str);

        Action action = new Action() {
            public void undo() {
                stringBuilder.replace(start, start + str.length(), strOld);
            }
        };
        if (actions.size() > bufferSize) {actions.remove(0);}
        actions.add(action);
        return this;
    }

    public StringBuilderUndo delete(int start, int end) {
        String strOld = stringBuilder.substring(start, end);
        stringBuilder.delete(start, end);

        Action action = new Action() {
            public void undo() {
                stringBuilder.insert(start, strOld);
            }
        };
        if (actions.size() > bufferSize) {actions.remove(0);}
        actions.add(action);
        return this;
    }

    public void undo(){
        if(!actions.isEmpty()){
            actions.pop().undo();
        }
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
