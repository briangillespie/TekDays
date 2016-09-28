import com.tekDays.*

class BootStrap {

    def init = { servletContext ->

        // Bootstrap TekUsers
        def user1 = new TekUser(fullName: 'John Doe',
                                userName: 'jdoe',
                                password: 't0ps3crET',
                                email: 'jdoe@gmail.com',
                                website: 'johnsgroovyshop.com',
                                bio: '''John has been programming for over 40 years. He has worked with
                                        every programming language known to man and has settled on Groovy.
                                        In his spare time, John dabbles in physics and shuffleboard.''')
        def user2 = new TekUser(fullName: 'Trolls for Trump',
                        userName: 'tftrump',
                        password: 't0ps3crET69',
                        email: 'tft@gmail.com',
                        website: 'trump.com',
                        bio: '''We host big DnD events and otherwise do stupid internet stuff to get attention
                                but we\'ve let the trump stuff go too far and now no one is sure if its even real.''')
        if(!user1.save()){
            user1.errors.allErrors.each{error ->
                println "An error occured with user1: ${error}"
            }
        }
        if(!user2.save()){
            user2.errors.allErrors.each{error ->
                println "An error occured with user2: ${error}"
            }
        }

        // Bootstrap TekEvents
    	def event1 = new TekEvent(name: 'Lords of DnD',
    							  city: 'Seattle, WA',
    							  organizer: TekUser.findByFullName('Trolls for Trump'),
    							  venue: 'WA Convention Center',
    							  startDate: new Date('9/27/2016'),
    							  endDate: new Date('9/29/2016'),
    							  description: 'A meeting of DnD players all joining together in a massive adventure led by legendary DM Basement Dweller!')
    	def event2 = new TekEvent(name: 'Gateway Code Camp',
    							  city: 'Seattle, WA',
    							  organizer: TekUser.findByFullName('John Doe'),
    							  venue: 'TBD',
    							  startDate: new Date('11/27/2016'),
    							  endDate: new Date('12/29/2016'),
    							  description: '''
    							  				This conference will bring coders from across platforms, languages, and industries
    							  				together for an exciting day of tips, tricks, and tech! Stay sharp! Stay at the top
    							  				of your game! But, don\'t stay home! Come and join us this fall for the first annual
    							  				Gateway Code Camp!!!
    							  			   ''')
        if(!event1.save()){
            event1.errors.allErrors.each{error ->
                println "An error occured with event1: ${error}"
            }
        }
    	if(!event2.save()){
    		event2.errors.allErrors.each{error ->
    			println "An error occured with event2: ${error}"
    		}
    	}

        // Add some volunteers to an event
        def user3 = new TekUser(fullName: 'Sarah Martin',
                                       userName: 'sarah',
                                       password: '54321',
                                       email: 'sarah@martinworld.com',
                                       website: 'www.martinworld.com',
                                       bio: 'Web designer and Grails master!')
        def user4 = new TekUser(fullName: 'Bill Smith',
                                       userName: 'Mr_Bill',
                                       password: '12345',
                                       email: 'mrbill@email.com',
                                       website: 'www.mrbillswebsite.com',
                                       bio: 'Software developer, claymation artist.')
        user3.save()
        user4.save()

        def g1 = TekEvent.findByName('Gateway Code Camp')
        g1.addToVolunteers(user3)
        g1.addToVolunteers(user4)

        // Add some respondents. (Email addresses of anon ppl interested in a TekEvent)
        g1.addToRespondents('ben@grailsmail.com')
        g1.addToRespondents('zaria@linuxgurus.com')
        g1.addToRespondents('sandy300@bootstrapwelding.com')

        g1.save()

        // Sponsors
        def s1 = new Sponsor(name: 'Contegix',
                         website:'http://www.contegix.com',
                         description:'Beyond Managed Hosting for Your Enterprise').save()
        def s2 = new Sponsor(name: 'Object Computing Incorporated',
                         website:'http://ociweb.com',
                         description:'An OO Software Company').save()

        // Sponsorships
        def sp1 = new Sponsorship(event:g1,
                                  sponsor: s1,
                                  contributionType:'Other',
                                  description:'Cool T-Shirts').save()
        def sp2 = new Sponsorship(event:g1,
                                  sponsor: s2,
                                  contributionType:'Venue',
                                  description:'Will be paying for the Moscone').save()

    }
    def destroy = {
    }
}
