import java.util.*;
import java.text.*;
import twitter4j.*;

public class bot{

    public static void delay(long d){
        long start=System.currentTimeMillis();
        long end=start+d;
        while(start<end){
            start=System.currentTimeMillis();
        }
        return;
    }

    public static void main(String[] args) throws TwitterException{

        //access the twitter API using twitter4j.properties file
        Twitter twitter = TwitterFactory.getSingleton();

        List<String> cities = new ArrayList<>();
        cities.add("#Sydney , #Melbourne and #Adelaide");
        cities.add("#Tokyo , #Seoul and #Darwin");
        cities.add("#Perth, #HongKong and #Beijing");
        cities.add("#Bangkok , #Hanoi and #Norilsk");
        cities.add("#Omsk");
        cities.add("#Oral");
        cities.add("#Dubai");
        cities.add("#Moscow , #Helinski and #Minsk");
        cities.add("#Paris , #Rome and #Berlin");
        cities.add("#London , #Lisbon and #Dublin");
        cities.add("#Reykjavik and #Freetown");
        cities.add("#Praia");
        cities.add("#Nuuk");
        cities.add("#Brasilia , #Rio and #SãoPaulo");
        cities.add("#NewYork , #Toronto and #WashingtonDC");
        cities.add("#Houston , #Winnipeg and #Panama");
        cities.add("#Denver , #Edmonton and #TheGalapagos");
        cities.add("#LosAngeles , #SanFrancisco and #Seattle");
        cities.add("#Anchorage");
        cities.add("#Honolulu");
        cities.add("#Alofi");
        cities.add("Blank");
        cities.add("#Auckland , #Wellington and #Pevek");
        cities.add("#PortVila");
        List<String> cityQ = new ArrayList<>();
        cityQ.add("Sydney");
        cityQ.add("Tokyo");
        cityQ.add("Hong Kong");
        cityQ.add("Bangkok");
        cityQ.add("Omsk");
        cityQ.add("Oral");
        cityQ.add("Dubai");
        cityQ.add("Moscow");
        cityQ.add("Paris");
        cityQ.add("London");
        cityQ.add("Reykjavik");
        cityQ.add("Praia");
        cityQ.add("Nuuk");
        cityQ.add("Rio");
        cityQ.add("New York");
        cityQ.add("Houston");
        cityQ.add("Denver");
        cityQ.add("Seattle");
        cityQ.add("Anchorage");
        cityQ.add("Honolulu");
        cityQ.add("Alofi");
        cityQ.add("Blank");
        cityQ.add("Auckland");
        cityQ.add("PortVila");
        List<GeoLocation> geo=new ArrayList<>();
        geo.add(new GeoLocation(-33.8688,151.2093));
        geo.add(new GeoLocation(35.6895,139.6917));
        geo.add(new GeoLocation(22.3964,114.1095));
        geo.add(new GeoLocation(13.7563,100.5018));
        geo.add(new GeoLocation(54.9885,73.3242));
        geo.add(new GeoLocation(51.2278,51.3865));
        geo.add(new GeoLocation(25.2048,55.2908));
        geo.add(new GeoLocation(55.7558,37.6173));
        geo.add(new GeoLocation(48.8566,2.3522));
        geo.add(new GeoLocation(51.5074,-0.1278));
        geo.add(new GeoLocation(64.1265,-21.8174));
        geo.add(new GeoLocation(14.9330,-23.5133));
        geo.add(new GeoLocation(64.1814,-51.6941));
        geo.add(new GeoLocation(-22.9068,-43.1729));
        geo.add(new GeoLocation(40.7128,-74.0059));
        geo.add(new GeoLocation(29.7604,-95.3698));
        geo.add(new GeoLocation(39.7392,-104.9903));
        geo.add(new GeoLocation(47.6062,-122.3321));
        geo.add(new GeoLocation(61.2181,-149.9003));
        geo.add(new GeoLocation(21.3069,-157.8583));
        geo.add(new GeoLocation(-19.0554,-169.9179));
        geo.add(new GeoLocation(0.0,0.0));
        geo.add(new GeoLocation(-36.8485,174.7633));
        geo.add(new GeoLocation(-17.7333,168.3273));

        //send a tweet
        SimpleDateFormat sdfa = new SimpleDateFormat("HH:mm:ss");
        String stuff=sdfa.format(new Date());
        System.out.println("Initiated at "+stuff);
        while(true){
            SimpleDateFormat sdf = new SimpleDateFormat("HH");
            stuff=sdfa.format(new Date());
            String str = sdf.format(new Date());
            int hour=Integer.parseInt(str);
            try{
                if(hour!=21&&hour!=5){
                    Query query = new Query();
                    query.setGeoCode(geo.get(hour),200,Query.KILOMETERS);
                    QueryResult result = twitter.search(query);
                    Status tweetResult = result.getTweets().get(0);
                    String user=tweetResult.getUser().getScreenName();
                    List<Status> statuses= twitter.getUserTimeline(user);

                    Status status = twitter.updateStatus("Good morning "+cities.get(hour)+"! Have a wonderful day today @"+user+" !");
                    List<String> sum=new ArrayList<>();
                    List<Integer> sums=new ArrayList<>();
                    for(Status s:statuses){
                      //System.out.println("@" + s.getUser().getScreenName() + " - " + s.getText());
                      String[] words=s.getText().split(" ");
                      for(String a:words){
                        if(!sum.contains(a)){
                          sum.add(a);
                          sums.add(1);
                        }else{
                          int b=sums.get(sum.indexOf(a));
                          b++;
                          sums.set(sum.indexOf(a),b);
                        }
                      }
                    }
                    int rec=0;int loops=0;int recLoop=0;
                    for(int i:sums){
                      loops++;
                      if(i>rec){
                        rec=i;
                        recLoop=loops;
                      }
                    }
                    System.out.println("@"+user+" has tweeted the word "+sum.get(recLoop)+" the most!");
                    System.out.println("Successful tweet at "+stuff);
                }
            }catch(TwitterException e){
                System.out.println(e);

            }



            delay((60*60*1000)-2000);
            System.out.println("Going again!");
        }
    }
}
