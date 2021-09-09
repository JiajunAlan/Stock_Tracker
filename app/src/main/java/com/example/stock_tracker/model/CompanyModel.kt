package com.example.stock_tracker.model

class CompanyModel(cname:String,
                   cfullname:String,
                   cprice:String,
                   chigh:String,
                   clow:String) {

    val name = cname
    val fullname = cfullname
    val price =cprice
    val high = chigh
    val low = clow

    /**@return Company Model toSting.
     * */
    override fun toString(): String {
        return "Company name: $name\n" +
                "Full name: $fullname\n" +
                "price now: $price\n" +
                "high: $high\n" +
                "low: $low"
    }
}