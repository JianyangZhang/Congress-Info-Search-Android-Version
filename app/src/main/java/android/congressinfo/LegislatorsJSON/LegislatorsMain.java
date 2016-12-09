package android.congressinfo.LegislatorsJSON;

/**
 * Created by jiany on 2016/11/19.
 */


public class LegislatorsMain
{
    private int count;

    private LegislatorsResults[] results;

    private LegislatorsPage page;

    public int getCount ()
    {
        return count;
    }

    public void setCount (int count)
    {
        this.count = count;
    }

    public LegislatorsResults[] getResults ()
    {
        return results;
    }

    public void setResults (LegislatorsResults[] results)
    {
        this.results = results;
    }

    public LegislatorsPage getPage ()
    {
        return page;
    }

    public void setPage (LegislatorsPage page)
    {
        this.page = page;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [count = "+count+", results = "+results+", page = "+page+"]";
    }
}
