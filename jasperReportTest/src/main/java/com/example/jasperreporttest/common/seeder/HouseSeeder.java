package com.example.jasperreporttest.common.seeder;

import com.example.jasperreporttest.model.House;
import com.example.jasperreporttest.repository.IHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HouseSeeder implements CommandLineRunner{

        @Autowired
        private IHouseRepository houseRepository;

        @Override
        public void run(String... args) throws Exception {
            loadHouses();
        }

        private void loadHouses() {
            if (houseRepository.count() == 0) {
                loadHousesSeed();
            }
        }

        private void loadHousesSeed() {
            houseRepository.save(buildHouse(1L, "name1", "address1"));
            houseRepository.save(buildHouse(2L, "name2", "address2"));
            houseRepository.save(buildHouse(3L, "name3", "address3"));
            houseRepository.save(buildHouse(4L, "name4", "address4"));
            houseRepository.save(buildHouse(5L, "name5", "address5"));
            houseRepository.save(buildHouse(6L, "name6", "address6"));
            houseRepository.save(buildHouse(7L, "name7", "address7"));
        }

        private House buildHouse(long id, String name, String address) {
            return new House(
                    name,
                    address);
        }
}
