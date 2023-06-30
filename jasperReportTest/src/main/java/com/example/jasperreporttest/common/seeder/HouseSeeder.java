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
            for (int i = 1; i <= 10; i++) {
                houseRepository.save(buildHouse("name " + i, "address " + i));
            }
        }

        private House buildHouse(String name, String address) {
            return new House(
                    name,
                    address);
        }
}
