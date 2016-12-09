package android.congressinfo.BillsJSON;

public class BillsMain
{
    private int count;

    private BillsResults[] results;

    private BillsPage page;

    public int getCount ()
    {
        return count;
    }

    public void setCount (int count)
    {
        this.count = count;
    }

    public BillsResults[] getResults ()
    {
        return results;
    }

    public void setResults (BillsResults[] results)
    {
        this.results = results;
    }

    public BillsPage getPage ()
    {
        return page;
    }

    public void setPage (BillsPage page)
    {
        this.page = page;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [count = "+count+", results = "+results+", page = "+page+"]";
    }
}

