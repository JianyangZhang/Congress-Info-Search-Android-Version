package android.congressinfo.CommitteesJSON;

public class CommitteesMain
{
    private int count;

    private CommitteesResults[] results;

    private CommitteesPage page;

    public int getCount ()
    {
        return count;
    }

    public void setCount (int count)
    {
        this.count = count;
    }

    public CommitteesResults[] getResults ()
    {
        return results;
    }

    public void setResults (CommitteesResults[] results)
    {
        this.results = results;
    }

    public CommitteesPage getPage ()
    {
        return page;
    }

    public void setPage (CommitteesPage page)
    {
        this.page = page;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [count = "+count+", results = "+results+", page = "+page+"]";
    }
}

