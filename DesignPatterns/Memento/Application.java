package Memento;

// The originator holds some important data that may change over time.
class Editor{
    private String text;
    private int curX,curY, selectionWidth;

    public void setText(String text) {
        this.text = text;
    }

    public void setCursor(int x, int y) {
        this.curY = y;
        this.curX = x;
    }

    public void setWidth(int width) {
        this.selectionWidth = width;
    }

    // Saves the current state inside a memento.
    public Snapshot createSnapshot() {
        return new Snapshot(this, text, curX, curY, selectionWidth);
    }
    public String getText(){
        return text;
    }
}


// The memento class stores the past state of the editor.
class Snapshot{
    private final Editor editor;
    private final String text;
    private final int curX, curY, selectionWidth;

    public Snapshot(Editor editor, String text, int curX, int curY, int selectionWidth) {
        this.editor = editor;
        this.text = text;
        this.curX = curX;
        this.curY = curY;
        this.selectionWidth = selectionWidth;
    }

    // At some point, a previous state of the editor can be
    // restored using a memento object.
    public void restore() {
        editor.setText(text);
        editor.setCursor(curX, curY);
        editor.setWidth(selectionWidth);
    }
}

// A command object can act as a caretaker. In that case, the
class Command{
    private Snapshot backup;
    private Editor editor;

    public Command(Editor editor){
        this.editor = editor;
    }

    public void makeBackup(){
        backup = editor.createSnapshot();
    }

    public void undo(){
        if (backup != null){
            backup.restore();
        }
    }

    public void execute(String newText){
        makeBackup();
        editor.setText(newText);
    }
}

public class Application {
    public static void main(String[] args){
        Editor editor = new Editor();
        Command command = new Command(editor);

        // Initial state
        editor.setText("Initial text");
        editor.setCursor(0, 0);
        editor.setWidth(10);
        System.out.println("Original state: " + editor.getText());

        // Change state with command
        command.execute("New text");
        System.out.println("State after change: " + editor.getText());

        // Undo the change
        command.undo();
        System.out.println("State after undo: " + editor.getText());

    }
}
