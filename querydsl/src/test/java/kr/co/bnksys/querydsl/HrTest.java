package kr.co.bnksys.querydsl;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.bnksys.querydsl.data.repository.CountryRepository;
import kr.co.bnksys.querydsl.data.repository.LocationRepository;
import kr.co.bnksys.querydsl.data.repository.RegionRepository;
import kr.co.bnksys.querydsl.model.hr.Country;
import kr.co.bnksys.querydsl.model.hr.Location;
import kr.co.bnksys.querydsl.model.hr.Region;

@SpringBootTest
class HrTest {

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    LocationRepository locationRepository;

    @Test
    void test() {
        assertNotNull(regionRepository);

        //saveRegionRepository();
        //saveCountries();
        saveLocations();

    }

    private void saveLocations() {
        Country IT = countryRepository.findById("IT").get();
        Country JP = countryRepository.findById("JP").get();
        Country US = countryRepository.findById("US").get();
        Country CA = countryRepository.findById("CA").get();
        Country CN = countryRepository.findById("CN").get();
        Country IN = countryRepository.findById("IN").get();
        Country AU = countryRepository.findById("AU").get();
        Country SG = countryRepository.findById("SG").get();
        Country UK = countryRepository.findById("UK").get();
        Country DE = countryRepository.findById("DE").get();
        Country BR = countryRepository.findById("BR").get();
        Country CH = countryRepository.findById("CH").get();
        Country NL = countryRepository.findById("NL").get();
        Country MX = countryRepository.findById("MX").get();

        List<Location> list = new ArrayList<>();
        list.add(new Location(1000L, "1297 Via Cola di Rie", "00989", "Roma", null, IT));
        list.add(new Location(1100L, "93091 Calle della Testa", "10934", "Venice", null, IT));
        list.add(new Location(1200L, "2017 Shinjuku-ku", "1689", "Tokyo", "Tokyo Prefecture", JP));
        list.add(new Location(1300L, "9450 Kamiya-cho", "6823", "Hiroshima", null, JP));
        list.add(new Location(1400L, "2014 Jabberwocky Rd", "26192", "Southlake", "Texas", US));
        list.add(new Location(1500L, "2011 Interiors Blvd", "99236", "South San Francisco", "California", US));
        list.add(new Location(1600L, "2007 Zagora St", "50090", "South Brunswick", "New Jersey", US));
        list.add(new Location(1700L, "2004 Charade Rd", "98199", "Seattle", "Washington", US));
        list.add(new Location(1800L, "147 Spadina Ave", "M5V 2L7", "Toronto", "Ontario", CA));
        list.add(new Location(1900L, "6092 Boxwood St", "YSW 9T2", "Whitehorse", "Yukon", CA));
        list.add(new Location(2000L, "40-5-12 Laogianggen", "190518", "Beijing", null, CN));
        list.add(new Location(2100L, "1298 Vileparle (E)", "490231", "Bombay", "Maharashtra", IN));
        list.add(new Location(2200L, "12-98 Victoria Street", "2901", "Sydney", "New South Wales", AU));
        list.add(new Location(2300L, "198 Clementi North", "540198", "Singapore", null, SG));
        list.add(new Location(2400L, "8204 Arthur St", null, "London", null, UK));
        list.add(new Location(2500L, "Magdalen Centre, The Oxford Science Park", "OX9 9ZB", "Oxford", "Oxford", UK));
        list.add(new Location(2600L, "9702 Chester Road", "09629850293", "Stretford", "Manchester", UK));
        list.add(new Location(2700L, "Schwanthalerstr 7031", "80925", "Munich", "Bavaria", DE));
        list.add(new Location(2800L, "Rua Frei Caneca 1360", "01307-002", "Sao Paulo", "Sao Paulo", BR));
        list.add(new Location(2900L, "20 Rue des Corps-Saints", "1730", "Geneva", "Geneve", CH));
        list.add(new Location(3000L, "Murtenstrasse 921", "3095", "Bern", "BE", CH));
        list.add(new Location(3100L, "Pieter Breughelstraat 837", "3029SK", "Utrecht", "Utrecht", NL));
        list.add(new Location(3200L, "Mariano Escobedo 9991", "11932", "Mexico City", "Distrito Federal", MX));

        locationRepository.saveAll(list);
    }

    private void saveCountries() {

        Region r1 = regionRepository.findById(1L).get();
        Region r2 = regionRepository.findById(2L).get();
        Region r3 = regionRepository.findById(3L).get();
        Region r4 = regionRepository.findById(4L).get();

        List<Country> list = new ArrayList<>();
        list.add(new Country("AR", "Argentina", r2));
        list.add(new Country("AU", "Australia", r3));
        list.add(new Country("BE", "Belgium", r1));
        list.add(new Country("BR", "Brazil", r2));
        list.add(new Country("CA", "Canada", r2));
        list.add(new Country("CH", "Switzerland", r1));
        list.add(new Country("CN", "China", r3));
        list.add(new Country("DE", "Germany", r1));
        list.add(new Country("DK", "Denmark", r1));
        list.add(new Country("EG", "Egypt", r4));
        list.add(new Country("FR", "France", r1));
        list.add(new Country("IL", "Israel", r4));
        list.add(new Country("IN", "India", r3));
        list.add(new Country("IT", "Italy", r1));
        list.add(new Country("JP", "Japan", r3));
        list.add(new Country("KW", "Kuwait", r4));
        list.add(new Country("ML", "Malaysia", r3));
        list.add(new Country("MX", "Mexico", r2));
        list.add(new Country("NG", "Nigeria", r4));
        list.add(new Country("NL", "Netherlands", r1));
        list.add(new Country("SG", "Singapore", r3));
        list.add(new Country("UK", "United Kingdom", r1));
        list.add(new Country("US", "United States of America", r2));
        list.add(new Country("ZM", "Zambia", r4));
        list.add(new Country("ZW", "Zimbabwe", r4));

        countryRepository.saveAll(list);

    }

    private void saveRegionRepository() {

        List<Region> list = new ArrayList<>();
        list.add(new Region(1L, "Europe"));
        list.add(new Region(2L, "Americas"));
        list.add(new Region(3L, "Asia"));
        list.add(new Region(4L, "Middle East and Africa"));

        regionRepository.saveAll(list);

    }

}
