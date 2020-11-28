package journey.guifx.planICS;

import journey.domain.Offer;
import journey.guifx.Layout;
import journey.guifx.configuration.ConfigurationPanel;
import journey.guifx.offerfx.OfferFX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.planics.concrete.sa.algorithm.Parameters;
import pl.planics.concrete.sa.application.Application;
import planics.common.concrete.IOffer;
import planics.common.concrete.IOfferSet;
import run.experiments.OffersFromFile;
import run.experiments.RunHSA;
import run.experiments.Utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class HSA extends Thread {
    private static final Logger LOG = LoggerFactory.getLogger(HSA.class);

    public HSA(boolean csv) {
        try {
            Parameters parameters = new Parameters();
            OffersFromFile input = new OffersFromFile(ConfigurationPanel.getPropertyTextField().getText(), ConfigurationPanel.getDataTextField().getText());
            Application se = new Application(parameters, input);
            LOG.info("PalICS HSA running...");
            se.run();

            double smtTime = se.getSmtTime();
            double quality = se.getQuality();

            Layout.getResult().clear();
            for(int i = 0; i < se.getResult().length; i++) {
                IOfferSet iOfferSet = input.getOfferSets().get(i);
                IOffer offer = iOfferSet.getOffer(se.getResult()[i]);
                Layout.getResult().add(new OfferFX(iOfferSet.getServiceTypeName(), new Offer(se.getResult()[i], i, offer.getIntegerValues(), offer.getDoubleValues())));
            }

            double javaTime = Utils.getCpuTimeSeconds();

            LOG.info("smtTime: " + smtTime);
            LOG.info("javaTime: " + javaTime);
            LOG.info("quality: " + quality);

            if (csv) {
                String stats = String.format("%f; %f", javaTime, quality);
                File f = new File(ConfigurationPanel.getPropertyTextField().getText());
                String name = f.getName().substring(0, f.getName().indexOf(".")) + "_HSA";
                LOG.info("Create csv file in " + new CSV(f.getParent(), stats, name).getCSVFile());
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(RunHSA.class.getName()).log(Level.SEVERE, null,ex);
        }
    }
}
