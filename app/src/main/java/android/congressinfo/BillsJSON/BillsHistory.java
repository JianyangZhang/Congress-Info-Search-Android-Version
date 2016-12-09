package android.congressinfo.BillsJSON;

public class BillsHistory
{
    private String senate_passage_result_at;

    private String senate_passage_result;

    private boolean vetoed;

    private String active_at;

    private boolean active;

    private boolean awaiting_signature;

    private boolean enacted;

    public String getSenate_passage_result_at ()
    {
        return senate_passage_result_at;
    }

    public void setSenate_passage_result_at (String senate_passage_result_at)
    {
        this.senate_passage_result_at = senate_passage_result_at;
    }

    public String getSenate_passage_result ()
    {
        return senate_passage_result;
    }

    public void setSenate_passage_result (String senate_passage_result)
    {
        this.senate_passage_result = senate_passage_result;
    }

    public boolean getVetoed ()
    {
        return vetoed;
    }

    public void setVetoed (boolean vetoed)
    {
        this.vetoed = vetoed;
    }

    public String getActive_at ()
    {
        return active_at;
    }

    public void setActive_at (String active_at)
    {
        this.active_at = active_at;
    }

    public boolean getActive ()
    {
        return active;
    }

    public void setActive (boolean active)
    {
        this.active = active;
    }

    public boolean getAwaiting_signature ()
    {
        return awaiting_signature;
    }

    public void setAwaiting_signature (boolean awaiting_signature)
    {
        this.awaiting_signature = awaiting_signature;
    }

    public boolean getEnacted ()
    {
        return enacted;
    }

    public void setEnacted (boolean enacted)
    {
        this.enacted = enacted;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [senate_passage_result_at = "+senate_passage_result_at+", senate_passage_result = "+senate_passage_result+", vetoed = "+vetoed+", active_at = "+active_at+", active = "+active+", awaiting_signature = "+awaiting_signature+", enacted = "+enacted+"]";
    }
}

