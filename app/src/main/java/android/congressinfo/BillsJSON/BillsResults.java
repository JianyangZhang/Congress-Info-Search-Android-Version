package android.congressinfo.BillsJSON;

public class BillsResults
{
    private String last_version_on;

    private String congress;

    private String sponsor_id;

    private BillsUrls urls;

    private String[] committee_ids;

    private String official_title;

    private String number;

    private BillsSponsor sponsor;

    private String bill_type;

    private String last_vote_at;

    private BillsHistory history;

    private String cosponsors_count;

    private String short_title;

    private String enacted_as;

    private String popular_title;

    private String[] related_bill_ids;

    private BillsLast_version last_version;

    private String last_action_at;

    private String withdrawn_cosponsors_count;

    private String introduced_on;

    private String bill_id;

    private String chamber;

    public String getLast_version_on ()
    {
        return last_version_on;
    }

    public void setLast_version_on (String last_version_on)
    {
        this.last_version_on = last_version_on;
    }

    public String getCongress ()
    {
        return congress;
    }

    public void setCongress (String congress)
    {
        this.congress = congress;
    }

    public String getSponsor_id ()
    {
        return sponsor_id;
    }

    public void setSponsor_id (String sponsor_id)
    {
        this.sponsor_id = sponsor_id;
    }

    public BillsUrls getUrls ()
    {
        return urls;
    }

    public void setUrls (BillsUrls urls)
    {
        this.urls = urls;
    }

    public String[] getCommittee_ids ()
    {
        return committee_ids;
    }

    public void setCommittee_ids (String[] committee_ids)
    {
        this.committee_ids = committee_ids;
    }

    public String getOfficial_title ()
    {
        return official_title;
    }

    public void setOfficial_title (String official_title)
    {
        this.official_title = official_title;
    }

    public String getNumber ()
    {
        return number;
    }

    public void setNumber (String number)
    {
        this.number = number;
    }

    public BillsSponsor getSponsor ()
    {
        return sponsor;
    }

    public void setSponsor (BillsSponsor sponsor)
    {
        this.sponsor = sponsor;
    }

    public String getBill_type ()
    {
        return bill_type;
    }

    public void setBill_type (String bill_type)
    {
        this.bill_type = bill_type;
    }

    public String getLast_vote_at ()
    {
        return last_vote_at;
    }

    public void setLast_vote_at (String last_vote_at)
    {
        this.last_vote_at = last_vote_at;
    }

    public BillsHistory getHistory ()
    {
        return history;
    }

    public void setHistory (BillsHistory history)
    {
        this.history = history;
    }

    public String getCosponsors_count ()
    {
        return cosponsors_count;
    }

    public void setCosponsors_count (String cosponsors_count)
    {
        this.cosponsors_count = cosponsors_count;
    }

    public String getShort_title ()
{
    return short_title;
}

    public void setShort_title (String short_title)
    {
        this.short_title = short_title;
    }

    public String getEnacted_as ()
{
    return enacted_as;
}

    public void setEnacted_as (String enacted_as)
    {
        this.enacted_as = enacted_as;
    }

    public String getPopular_title ()
{
    return popular_title;
}

    public void setPopular_title (String popular_title)
    {
        this.popular_title = popular_title;
    }

    public String[] getRelated_bill_ids ()
    {
        return related_bill_ids;
    }

    public void setRelated_bill_ids (String[] related_bill_ids)
    {
        this.related_bill_ids = related_bill_ids;
    }

    public BillsLast_version getLast_version ()
    {
        return last_version;
    }

    public void setLast_version (BillsLast_version last_version)
    {
        this.last_version = last_version;
    }

    public String getLast_action_at ()
    {
        return last_action_at;
    }

    public void setLast_action_at (String last_action_at)
    {
        this.last_action_at = last_action_at;
    }

    public String getWithdrawn_cosponsors_count ()
    {
        return withdrawn_cosponsors_count;
    }

    public void setWithdrawn_cosponsors_count (String withdrawn_cosponsors_count)
    {
        this.withdrawn_cosponsors_count = withdrawn_cosponsors_count;
    }

    public String getIntroduced_on ()
    {
        return introduced_on;
    }

    public void setIntroduced_on (String introduced_on)
    {
        this.introduced_on = introduced_on;
    }

    public String getBill_id ()
    {
        return bill_id;
    }

    public void setBill_id (String bill_id)
    {
        this.bill_id = bill_id;
    }

    public String getChamber ()
    {
        return chamber;
    }

    public void setChamber (String chamber)
    {
        this.chamber = chamber;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [last_version_on = "+last_version_on+", congress = "+congress+", sponsor_id = "+sponsor_id+", urls = "+urls+", committee_ids = "+committee_ids+", official_title = "+official_title+", number = "+number+", sponsor = "+sponsor+", bill_type = "+bill_type+", last_vote_at = "+last_vote_at+", history = "+history+", cosponsors_count = "+cosponsors_count+", short_title = "+short_title+", enacted_as = "+enacted_as+", popular_title = "+popular_title+", related_bill_ids = "+related_bill_ids+", last_version = "+last_version+", last_action_at = "+last_action_at+", withdrawn_cosponsors_count = "+withdrawn_cosponsors_count+", introduced_on = "+introduced_on+", bill_id = "+bill_id+", chamber = "+chamber+"]";
    }
}

