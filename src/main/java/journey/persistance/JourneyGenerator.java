package journey.persistance;

import journey.app.LoggingMain;
import journey.domain.*;
import planics.common.concrete.IOffer;
import planics.common.concrete.IOfferSet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JourneyGenerator extends LoggingMain {

    public JourneySolution createJourneySolution() {
        List<Offer> offers = new ArrayList<>();
        offers.add(new Offer(0, 0, new long[]{0, 1, 17, 18, 1189}, new double[]{}));
        offers.add(new Offer(1, 0, new long[]{0, 1, 15, 16, 1217}, new double[]{}));
        offers.add(new Offer(2, 0, new long[]{0, 1, 16, 18, 1188}, new double[]{}));
        offers.add(new Offer(3, 0, new long[]{0, 2, 15, 16, 1119}, new double[]{}));
        offers.add(new Offer(4, 0, new long[]{0, 2, 17, 17, 1426}, new double[]{}));
        offers.add(new Offer(5, 0, new long[]{0, 2, 16, 16, 1939}, new double[]{}));

        offers.add(new Offer(6, 1, new long[]{1862, 18, 22, 1}, new double[]{8.7}));
        offers.add(new Offer(7, 1, new long[]{4048, 18, 24, 1}, new double[]{9.0}));
        offers.add(new Offer(8, 1, new long[]{3511, 16, 23, 1}, new double[]{8.6}));
        offers.add(new Offer(9, 1, new long[]{5951, 16, 23, 2}, new double[]{8.5}));
        offers.add(new Offer(10, 1, new long[]{4232, 16, 21, 2}, new double[]{8.3}));
        offers.add(new Offer(11, 1, new long[]{7006, 17, 24, 2}, new double[]{8.3}));

        offers.add(new Offer(12, 2, new long[]{1, 2, 22, 22, 2633}, new double[]{}));
        offers.add(new Offer(13, 2, new long[]{1, 2, 23, 24, 2334}, new double[]{}));
        offers.add(new Offer(14, 2, new long[]{1, 2, 24, 24, 2747}, new double[]{}));
        offers.add(new Offer(15, 2, new long[]{2, 1, 21, 23, 2480}, new double[]{}));
        offers.add(new Offer(16, 2, new long[]{2, 1, 23, 24, 3812}, new double[]{}));
        offers.add(new Offer(17, 2, new long[]{2, 1, 24, 26, 3073}, new double[]{}));

        offers.add(new Offer(18, 3, new long[]{653, 22, 29, 1}, new double[]{8.1}));
        offers.add(new Offer(19, 3, new long[]{850, 22, 30, 1}, new double[]{8.5}));
        offers.add(new Offer(20, 3, new long[]{1662, 24, 29, 1}, new double[]{8.3}));
        offers.add(new Offer(21, 3, new long[]{2551, 23, 29, 2}, new double[]{8.4}));
        offers.add(new Offer(22, 3, new long[]{2921, 24, 30, 2}, new double[]{8.2}));
        offers.add(new Offer(23, 3, new long[]{2009, 26, 30, 2}, new double[]{8.2}));

        offers.add(new Offer(24, 4, new long[]{2, 0, 30, 30, 4924}, new double[]{}));
        offers.add(new Offer(25, 4, new long[]{2, 0, 29, 29, 3283}, new double[]{}));
        offers.add(new Offer(26, 4, new long[]{2, 0, 29, 30, 3047}, new double[]{}));
        offers.add(new Offer(27, 4, new long[]{1, 0, 29, 29, 1155}, new double[]{}));
        offers.add(new Offer(28, 4, new long[]{1, 0, 29, 29, 2206}, new double[]{}));
        offers.add(new Offer(29, 4, new long[]{1, 0, 30, 30, 1323}, new double[]{}));

        List<JourneyStage> journeyStages = new ArrayList<>();
        journeyStages.add(new JourneyStage(0, ""));
        journeyStages.add(new JourneyStage(1, ""));
        journeyStages.add(new JourneyStage(2, ""));
        journeyStages.add(new JourneyStage(3, ""));
        journeyStages.add(new JourneyStage(4, ""));

        return new JourneySolution(0, offers, journeyStages);
    }

    public JourneySolution createJourneySolutionFromFile(String dataFile) {
        List<IOfferSet> offers = loadOffersFromFile(dataFile);

        List<Offer> offerList = new ArrayList<>();
        List<JourneyStage> journeyStages = new ArrayList<>();
        int id = 0;
        for (IOfferSet offerSet : offers) {
            int offerSetId = 0;
            for(IOffer offer : offerSet.getOffers()) {
                offerSetId = offer.getServiceId();
                offerList.add(new Offer(id, offerSetId, offer.getIntegerValues(), offer.getDoubleValues()));
                id++;
            }
            journeyStages.add(new JourneyStage(offerSetId, offerSet.getServiceTypeName()));
        }
        System.out.println("Offers - " + offerList.size());
        return new JourneySolution(0, offerList, journeyStages);
    }

    private List<IOfferSet> loadOffersFromFile(String dataFile) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataFile))){
            return (List<IOfferSet>)in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(JourneyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Boolean getOfferFromResult(String result, IOffer offer) {
        String[] results = result.split(" ");
        for(String s : results) {
            String[] s1 = s.split(":");
            if(offer.getServiceId() == Integer.parseInt(s1[0]) && offer.getId() == Integer.parseInt(s1[1])) {
                return true;
            }
        }
        return false;
    }
}
