package task1;
public class CsvFormatter implements ReportFormatter {

    @Override
    public String format(String title, String[] headers, String[][] rows) {
        StringBuilder sb = new StringBuilder();
        sb.append("# TITLE: ").append(title).append("\n");
        sb.append(joinQuoted(headers)).append("\n");
        for (String[] row : rows) {
            sb.append(joinQuoted(row)).append("\n");
        }
        return sb.toString();
    }

    private static String joinQuoted(String[] cells) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < cells.length; i++) {
            String cell = cells[i] == null ? "" : cells[i];
            line.append('"').append(cell.replace("\"", "\"\"")).append('"');
            if (i < cells.length - 1) line.append(',');
        }
        return line.toString();
    }
}
