package task1;
public abstract class ExporterDecorator implements Exporter {
    protected final Exporter wrappedExporter;

    public ExporterDecorator(Exporter wrappedExporter) {
        if (wrappedExporter == null) {
            throw new IllegalArgumentException("Wrapped exporter cannot be null");
        }
        this.wrappedExporter = wrappedExporter;
    }

    protected abstract String transform(String data);

    @Override
    public void export(String destination, String data) {
        findBaseExporter().export(destination, applyLayers(data));
    }

    private String applyLayers(String data) {
        String innerResult = (wrappedExporter instanceof ExporterDecorator)
                ? ((ExporterDecorator) wrappedExporter).applyLayers(data)
                : data;
        return transform(innerResult);
    }
    private Exporter findBaseExporter() {
        return (wrappedExporter instanceof ExporterDecorator)
                ? ((ExporterDecorator) wrappedExporter).findBaseExporter()
                : wrappedExporter;
    }
}
