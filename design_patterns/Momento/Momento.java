package design_patterns.Momento;

import java.util.Stack;

public class Momento {
    static class SnapShot {
        String state;
        Editor editor;

        public SnapShot(String state, Editor editor) {
            this.state = state;
            this.editor = editor;
        }

    }

    static class Editor {
        String state;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public SnapShot creSnapShot() {
            return new SnapShot(state, this);
        }

    }

    static class Caretaker {
        Stack<SnapShot> history;

        public Caretaker() {
            history = new Stack<>();
        }

        public void undo() {
            if (!history.isEmpty()) {
                SnapShot s = history.pop();
                s.editor.setState(s.state);
            } else {
                throw new IllegalArgumentException("No history");
            }
        }

        public void saveSnapShot(SnapShot snapShot){
            history.push(snapShot);
        }
    }

    public static void main(String[] args) {
        Caretaker ct = new Caretaker();
        Editor editor = new Editor();
        editor.setState("ABHISHEK");

        ct.saveSnapShot(editor.creSnapShot());
        editor.setState("ASHUTOSH");

        ct.saveSnapShot(editor.creSnapShot());
        editor.setState("XYZ");
        System.out.println(editor.state);
        ct.undo();
        System.out.println(editor.state);
        ct.undo();
        System.out.println(editor.state);


    }
}
