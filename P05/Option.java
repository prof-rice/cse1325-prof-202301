public class Option {
    public Option(String name, long cost) {
        if(cost < 0) throw new IllegalArgumentException("Negative cost for " + name + ": " + cost);
        this.name = name;
        this.cost = cost;
    }
    public long cost() {
        return this.cost;
    }
    @Override
    public String toString() {
        return name + " ($" + String.format("%.2f", cost / 100.0) + ")";
    }
    @Override
    public boolean equals(Object o) {
        try {
            if(this == o) return true;
            if(this.getClass() != o.getClass()) return false;
            Option p = (Option) o;
            return this.name.equals(p.name) && (this.cost == p.cost);
        } catch (Exception e) {
            return false;
        }
    }

    protected String name;
    protected long cost;
}
