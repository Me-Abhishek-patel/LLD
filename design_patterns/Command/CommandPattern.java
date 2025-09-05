package design_patterns.Command;

import java.util.Stack;

public class CommandPattern {
    
    public static class Editor {
        private String text;
        
        public Editor(String text) {
            this.text = text;
        }
        
        public void setText(String text) {
            this.text = text;
        }
        
        public String getText() {
            return text;
        }
    }

    public interface Command {
        void execute();
        void undo();
    }

    public static class CommandHistory {
        private Stack<Command> history = new Stack<>();
        
        public void push(Command c) {
            history.push(c);
        }
        
        public Command pop() {
            return history.isEmpty() ? null : history.pop();
        }
        
        public boolean isEmpty() {
            return history.isEmpty();
        }
    }

    public static class Clear implements Command {
        private Editor editor;
        private String backupText;

        public Clear(Editor editor) {
            this.editor = editor;
        }

        @Override
        public void execute() {
            backupText = editor.getText(); // Backup for undo
            editor.setText("NONE");
        }

        @Override
        public void undo() {
            editor.setText(backupText);
        }
    }

    public static class Copy implements Command {
        private Editor editor;
        private String backupText;

        public Copy(Editor editor) {
            this.editor = editor;
        }

        @Override
        public void execute() {
            backupText = editor.getText(); // Backup for undo
            editor.setText(editor.getText() + editor.getText());
        }

        @Override
        public void undo() {
            editor.setText(backupText);
        }
    }

    public static class Button {
        private Command command;
        private CommandHistory history;

        public Button() {
            this.history = new CommandHistory();
        }

        public void onClick() {
            if (command != null) {
                command.execute();
                history.push(command);
            }
        }

        public void undo() {
            Command command = history.pop();
            if (command != null) {
                command.undo();
            }
        }

        public void setCommand(Command command) {
            this.command = command;
        }
    }

    public static void main(String[] args) {
        Editor editor = new Editor("Abhishek");
        Button btn = new Button();
        
        // Test Copy command
        System.out.println("Initial text: " + editor.getText());
        
        Command copy = new Copy(editor);
        btn.setCommand(copy);
        btn.onClick();
        System.out.println("After copy: " + editor.getText());
        
        // Test Clear command
        Command clear = new Clear(editor);
        btn.setCommand(clear);
        btn.onClick();
        System.out.println("After clear: " + editor.getText());
        
        // Test Undo
        btn.undo();
        System.out.println("After first undo (undoing clear): " + editor.getText());
        
        btn.undo();
        System.out.println("After second undo (undoing copy): " + editor.getText());
    }
}

