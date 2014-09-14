package org.gnucash.android.acra;

import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;
import org.gnucash.android.export.Exporter;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SDCardSaveSender implements ReportSender {
    private static final String REPORT_FOLDER_PATH = Exporter.EXPORT_FOLDER_PATH + "crash/";

    @Override
    public void send(CrashReportData report) throws ReportSenderException {
        try {
            new File(REPORT_FOLDER_PATH).mkdirs();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US);
            String filename = formatter.format(
                    new Date(System.currentTimeMillis()))
                    + "_gnucash_crash.txt";
            FileWriter fileWriter = new FileWriter(REPORT_FOLDER_PATH + filename);
            fileWriter.write(report.toJSON().toString());
            fileWriter.close();
        }
        catch (Exception e) {
            throw new ReportSenderException("Error generation report", e);
        }
    }
}
