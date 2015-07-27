
public class Range {
    protected int lo;
    protected int hi;

    public boolean contains(int x) {
        return this.lo <= x && x <= this.hi;
    }
    public boolean contains(Range r) {
        return this.lo <= r.lo && r.hi <= this.hi;
    }
    public boolean equals(Range r) {
        return this.lo == r.lo && this.hi == r.hi;
    }
    public boolean isDisjoint(Range r) {
        return this.lo > r.hi || this.hi < r.lo;
    }
    public boolean isOverlapping(Range r) {
        return !(this.isDisjoint(r));
    } 
    public boolean lessThan(Range r) {
        return this.lo < r.lo;
    }
 
    public String classify(Range r) {
        if (this.equals(r))
            return "SAME";
        if (this.contains(r)) 
            return "SUPERSET";
        if (r.contains(this))
            return "SUBSET";
        if (this.isDisjoint(r))
            if (this.lo > r.hi)
                return "MOREDISJOINT";
            else
                return "LESSDISJOINT";
        if (this.lessThan(r))
            return "LESSOVERLAP";
        return "MOREOVERLAP";
    }
}
