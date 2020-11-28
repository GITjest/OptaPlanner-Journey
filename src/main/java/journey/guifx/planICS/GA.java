package journey.guifx.planICS;


import journey.domain.Offer;
import journey.guifx.Layout;
import journey.guifx.configuration.ConfigurationPanel;
import journey.guifx.offerfx.OfferFX;
import org.slf4j.LoggerFactory;
import pl.planics.concrete.ga.ga.Application;
import pl.planics.concrete.ga.ga.Parameters;
import planics.common.concrete.IOffer;
import planics.common.concrete.IOfferSet;
import planics.common.concrete.NoSolutionException;
import run.experiments.OffersFromFile;
import run.experiments.Utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GA extends Thread {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GA.class);

    public GA(boolean csv) {
        try {
            Parameters parameters = new Parameters();

            OffersFromFile input = new OffersFromFile(ConfigurationPanel.getPropertyTextField().getText(), ConfigurationPanel.getDataTextField().getText());

            Application se = new Application(parameters, input);
            try {
                LOG.info("PalICS GA running...");
                se.run();

                double quality = se.getQuality();
                double javaTime = Utils.getCpuTimeSeconds();

                Layout.getResult().clear();
                for(int i = 0; i < se.getResult().length; i++) {
                    IOfferSet iOfferSet = input.getOfferSets().get(i);
                    IOffer offer = iOfferSet.getOffer(se.getResult()[i]);
                    Layout.getResult().add(new OfferFX(iOfferSet.getServiceTypeName(), new Offer(se.getResult()[i], i, offer.getIntegerValues(), offer.getDoubleValues())));
                }

                LOG.info("javaTime: " + javaTime);
                LOG.info("quality: " + quality);
                if (csv) {
                    String stats = String.format("%f; %f", javaTime, quality);
                    File f = new File(ConfigurationPanel.getPropertyTextField().getText());
                    String name = f.getName().substring(0, f.getName().indexOf(".")) + "_GA";
                    LOG.info("Create csv file in " + new CSV(f.getParent(), stats, name).getCSVFile());
                }
            } catch (NoSolutionException ex) {
                LOG.info(String.valueOf(ex));
                System.out.println(ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(GA.class.getName()).log(Level.SEVERE, null,ex);
        }
    }

}
