package android.congressinfo.BillsJSON;

public class BillsPage
{
    private String per_page;

    private int count;

    private String page;

    public String getPer_page ()
    {
        return per_page;
    }

    public void setPer_page (String per_page)
    {
        this.per_page = per_page;
    }

    public int getCount ()
    {
        return count;
    }

    public void setCount (int count)
    {
        this.count = count;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [per_page = "+per_page+", count = "+count+", page = "+page+"]";
    }
}

