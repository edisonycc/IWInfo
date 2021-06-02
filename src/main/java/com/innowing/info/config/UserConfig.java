package com.innowing.info.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

//    @Bean
//    @Autowired
//    CommandLineRunner commandLineRunner (StaffRepository userRepository, MemberRepository memberRepository) {
//        return args -> {
//
//            Faker faker = new Faker();
//            Random rand = new Random();
//            String[] genders = {"M", "F"};
//            String[] depts = {"ME", "CS", "CIVIL", "IMSE", "EEE"};
//            String[] programmes = {"BEng", "BME", "BASc(FinTech)", "BEng(EngSc)", "BEng(CompSc)", "BEng(EE)", "BEng(CivE)"};
//            //String[] userTypes = {"member","staff"};
//            String[] userTypes = {"member"};
//            String[] staffTypes = {"inneral", "technician", "tutor", "academic", "research_support"};
//            Integer[] studyYears = {2018, 2019, 2020, 2021};
//            String[] titles = {"Mr.", "Miss"};
//
//            for (int i = 0; i<1; i++) {
//                Long hkuid = Math.abs(rand.nextLong());
//                String name = faker.name().name();
//                //tring lastName = faker.name().lastName();
//                String email = String.format("%s@hku.hk", name);
//                String gender = genders[rand.nextInt(genders.length)];
//                String dept = depts[rand.nextInt(depts.length)];
//                String userType = userTypes[rand.nextInt(userTypes.length)];
//                String staffType = staffTypes[rand.nextInt(staffTypes.length)];
//                String title = titles[rand.nextInt(staffTypes.length)];
//                Integer studyYear = studyYears[rand.nextInt(studyYears.length)];
//                String programName = programmes[rand.nextInt(programmes.length)];
////
//                if (userType == "member") {
//                    Member member = new Member(
//                            hkuid,
//                            name,
//                            email,
//                            gender,
//                            Math.abs(rand.nextLong()),
//                            "Engg",
//                            dept,
//                            studyYear,
//                            programName,
//                            false);
//
//                } else {
//                    Staff staff = new Staff(
//                            hkuid,
//                            name,
//                            email,
//                            gender,
//                            Math.abs(rand.nextLong()),
//                            "Engg",
//                            dept,
//                            staffType,
//                            title
//                    );
//                    userRepository.save(staff);
//                }
//            }
//        };
//    }
}
