package services;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;

import com.google.api.services.analyticsreporting.v4.model.ColumnHeader;
import com.google.api.services.analyticsreporting.v4.model.DateRange;
import com.google.api.services.analyticsreporting.v4.model.DateRangeValues;
import com.google.api.services.analyticsreporting.v4.model.GetReportsRequest;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.google.api.services.analyticsreporting.v4.model.Metric;
import com.google.api.services.analyticsreporting.v4.model.Dimension;
import com.google.api.services.analyticsreporting.v4.model.MetricHeaderEntry;
import com.google.api.services.analyticsreporting.v4.model.Report;
import com.google.api.services.analyticsreporting.v4.model.ReportRequest;
import com.google.api.services.analyticsreporting.v4.model.ReportRow;
import org.json.JSONArray;
import org.json.JSONObject;
import play.Logger;

public class HelloAnalyticsReporting {
  private static final String APPLICATION_NAME = "Hello Analytics Reporting";
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  private static final String KEY_FILE_LOCATION = "outstanding-map-209903-c6bd2b9f05ef.json";
//  private static final String VIEW_ID = "185396677";
  private static final String VIEW_ID = "185457510";
  public static JSONArray main() {
    try {
      AnalyticsReporting service = initializeAnalyticsReporting();

      GetReportsResponse response = getReport(service);
       return printResponse(response);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return new JSONArray();
  }

  /**
   * Initializes an Analytics Reporting API V4 service object.
   *
   * @return An authorized Analytics Reporting API V4 service object.
   * @throws IOException
   * @throws GeneralSecurityException
   */
  private static AnalyticsReporting initializeAnalyticsReporting() throws GeneralSecurityException, IOException {
    HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    GoogleCredential credential = GoogleCredential
        .fromStream(new FileInputStream(new File("./public/google_analytics/" + KEY_FILE_LOCATION)))
        .createScoped(AnalyticsReportingScopes.all());

    // Construct the Analytics Reporting service object.
    return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, credential)
        .setApplicationName(APPLICATION_NAME).build();
  }

  /**
   * Queries the Analytics Reporting API V4.
   *
   * @param service An authorized Analytics Reporting API V4 service object.
   * @return GetReportResponse The Analytics Reporting API V4 response.
   * @throws IOException
   */
  private static GetReportsResponse getReport(AnalyticsReporting service) throws IOException {
    // Create the DateRange object.
    DateRange dateRange = new DateRange();
    dateRange.setStartDate("60DaysAgo");
    dateRange.setEndDate("today");

    List<Metric> listMetric = new ArrayList<>();
    // Create the Metrics object.
    Metric sessions = new Metric().setExpression("ga:organicSearches").setAlias("organicSearches");
    Metric sessions1 = new Metric().setExpression("ga:pageviews").setAlias("pageviews");

    listMetric.add(sessions1);

    List<Dimension> listDimension = new ArrayList<>();

    Dimension dimension = new Dimension().setName("ga:source");
    Dimension dimension1 = new Dimension().setName("ga:referralPath");
    Dimension dimension2 = new Dimension().setName("ga:date");
    Dimension dimension3 = new Dimension().setName("ga:pagePath");
    listDimension.add(dimension);
    listDimension.add(dimension1);
    listDimension.add(dimension2);
    listDimension.add(dimension3);

    // Create the ReportRequest object.
    ReportRequest request = new ReportRequest()
        .setViewId(VIEW_ID)
        .setDateRanges(Arrays.asList(dateRange))
        .setMetrics(listMetric)
        .setDimensions(listDimension);

    ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
    requests.add(request);

    // Create the GetReportsRequest object.
    GetReportsRequest getReport = new GetReportsRequest()
        .setReportRequests(requests);

    // Call the batchGet method.
    GetReportsResponse response = service.reports().batchGet(getReport).execute();

    // Return the response.
    return response;
  }

  /**
   * Parses and prints the Analytics Reporting API V4 response.
   *
   * @param response An Analytics Reporting API V4 response.
   */
  private static JSONArray printResponse(GetReportsResponse response) {

    JSONArray jsonArrayData = new JSONArray();

    for (Report report: response.getReports()) {
      Logger.info("----------------------------------------------------------------------------132--");
      ColumnHeader header = report.getColumnHeader();
      List<String> dimensionHeaders = header.getDimensions();
      List<MetricHeaderEntry> metricHeaders = header.getMetricHeader().getMetricHeaderEntries();
      List<ReportRow> rows = report.getData().getRows();

      if (rows == null) {
//         System.out.println("No data found for " + VIEW_ID);
         return new JSONArray();
      }

      for (ReportRow row: rows) {
        JSONObject jsonObject = new JSONObject();
        List<String> dimensions = row.getDimensions();
        List<DateRangeValues> metrics = row.getMetrics();

//        Logger.info("--------------------------");

        for (int i = 0; i < dimensionHeaders.size() && i < dimensions.size(); i++) {
//          System.out.println(dimensionHeaders.get(i) + ": " + dimensions.get(i));
          jsonObject.put(dimensionHeaders.get(i), dimensions.get(i));
       	}

        JSONArray jsonArrayMetrics = new JSONArray();
        for (int j = 0; j < metrics.size(); j++) {
//          System.out.print("Date Range (" + j + "): ");
          DateRangeValues values = metrics.get(j);
          JSONObject jsonObjectMetric = new JSONObject();
          for (int k = 0; k < values.getValues().size() && k < metricHeaders.size(); k++) {
//            System.out.println(metricHeaders.get(k).getName() + ": " + values.getValues().get(k));
            jsonObjectMetric.put(metricHeaders.get(k).getName(), values.getValues().get(k));
          }

          jsonArrayMetrics.put(jsonObjectMetric);
        }

        jsonObject.put("data", jsonArrayMetrics);
        jsonArrayData.put(jsonObject);
      }

    }

//    Logger.info(jsonArrayData.toString());

    return jsonArrayData;
  }
}
