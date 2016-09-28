package com.tekDays

class TekEvent {

	String name
	String city
	String description
	TekUser organizer //TODO: This will became a domain object later
	String venue //TODO: This will became a domain object later
	Date startDate
	Date endDate

    static hasMany = [volunteers: TekUser, 
                      respondents: String, 
                      sponsorships: Sponsorship,
                      tasks: Task,
                      messages: TekMessage]

    static constraints = {
    	name()
    	city()
    	description maxSize: 2500
    	organizer()
    	venue()
    	startDate()
    	endDate()
        volunteers nullable: true
        sponsorships nullable: true
        tasks nullable: true
        messages nullable: true
    }

    String toString(){
        "$name, $city"
    }
}
