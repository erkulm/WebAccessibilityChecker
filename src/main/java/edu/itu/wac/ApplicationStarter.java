package edu.itu.wac;

import edu.itu.wac.entity.SubPageErrors;
import edu.itu.wac.entity.Website;
import edu.itu.wac.entity.WebsiteCategory;
import edu.itu.wac.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStarter {
    private final WebsiteRepository websiteRepository;
    private final WebsiteCategoryRepository websiteCategoryRepository;
    private final ErrorRepository errorRepository;
    private final SubPageErrorsRepository subPageErrorsRepository;
    private final ErrorReportRepository errorReportRepository;

    @Autowired
    public ApplicationStarter(WebsiteRepository websiteRep, WebsiteCategoryRepository websiteCategoryRep,
                              ErrorRepository errorReportRep,SubPageErrorsRepository subPageErrorsRepository,
                              ErrorReportRepository errorReportRepository) {
        this.websiteRepository = websiteRep;
        this.websiteCategoryRepository = websiteCategoryRep;
        this.errorRepository = errorReportRep;
        this.subPageErrorsRepository = subPageErrorsRepository;
        this.errorReportRepository = errorReportRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

//        websiteRepository.deleteAll();
//        websiteCategoryRepository.deleteAll();
//        errorRepository.deleteAll();
//        subPageErrorsRepository.deleteAll();
//        errorReportRepository.deleteAll();
////        String kafeinAddress = "https://www.kafein.com/";
//        Website kafeinWebsite = websiteRepository.findByAddress(kafeinAddress).orElseGet(() -> getNewWebsite(kafeinAddress));
//        List<Error> error = Pa11yUtil.runPa11y(kafeinWebsite, "", kafeinWebsite.getCategory());
//        errorReportRepository.saveAll(error);
//        List<Error> allErrors = errorReportRepository.findAll();
//        allErrors.forEach(System.out::println);


//
//        System.out.println("hello world, I have just started up");
//        websiteRepository.deleteAll();
//        websiteCategoryRepository.deleteAll();
//
//        // save a couple of customers
//        WebsiteCategory bigCompanies = new WebsiteCategory("Big Companies");
//        Website google = new Website("www.google.com");
//        websiteCategoryRepository.save(bigCompanies);
//        google.setCategory(bigCompanies);
//        websiteRepository.save(google);
//        websiteRepository.save(new Website("www.twitter.com"));
//
//        // fetch all customers
//        System.out.println("Customers found with findAll():");
//        System.out.println("-------------------------------");
//        for (Website website : websiteRepository.findAll()) {
//            System.out.println(website);
//        }
//        System.out.println();
//
//        // fetch an individual customer
//        System.out.println("Website found with findByName('www.google.com'):");
//        System.out.println("--------------------------------");
//        System.out.println(websiteRepository.findByName("www.google.com"));
//
//        System.out.println("Customers found with findByLastName('Smith'):");
//        System.out.println("--------------------------------");
//        for (Website website : websiteRepository.findAll()) {
//            System.out.println(website);
//        }

    }

    private Website getNewWebsite(String kafeinAddress) {
        Website website = new Website();
        website.setAddress(kafeinAddress);
        WebsiteCategory websiteCategory = new WebsiteCategory();
        websiteCategory.setName("General");
        websiteCategory = websiteCategoryRepository.save(websiteCategory);
        website.setCategory(websiteCategory);
        website = websiteRepository.save(website);
        return website;
    }
}
