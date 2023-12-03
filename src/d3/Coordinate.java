package d3;

public record Coordinate(int y, int x, String content){
    public boolean isSymbol() {
        return !isDigit() && !content.equals(".");
    }

    public boolean isDigit() {
        return Character.isDigit(content.toCharArray()[0]);
    }
}
