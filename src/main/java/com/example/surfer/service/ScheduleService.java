package com.example.surfer.service;

import com.example.surfer.dto.ScheduleDto;
import com.example.surfer.exception.CustomException;
import com.example.surfer.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.surfer.exception.ErrorCode.INVALID_FORM_DATA;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private final FastApiClient fastApiClient;

    public List<ScheduleDto> getSchedules(){
        return scheduleRepository.getSchedules();
    }

    public List<ScheduleDto> createSchedules(String text){

//        List<String> plans = fastApiClient.getSchedule(text);
        List<String> plans = new ArrayList<>();

        String jsonString = "{\n" +
                "  \"events\": [\n" +
                "    \"2024년8월 학부과정 수료대상자 및 졸업예정자 학업계속신청 마감일 | 2024.07.16 | 2024.07.16\",\n" +
                "    \"2024년8월 졸업예정자에 대한 학사학위취득유예 신청/접수 시작 | 2024.08.01 | 2024.08.01\"\n" +
                "  ]\n" +
                "}";

        // JSON 객체 생성
        JSONObject jsonObject = null;

        JSONArray eventsArray = null;

        try {
            jsonObject = new JSONObject(jsonString);

            // "events" 배열 가져오기
            eventsArray = jsonObject.getJSONArray("events");

            // 배열 안의 각 이벤트 문자열 가져오기
            for (int i = 0; i < eventsArray.length(); i++) {
                plans.add(eventsArray.getString(i));
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }



        List<ScheduleDto> result = new ArrayList<>();
        for(String plan : plans){
            String[] str = plan.split("\\|");

            ScheduleDto scheduleDto = null;
            if(str.length > 1) {

                String st = str[1].replace(" ", "");
                String end = str[2].replace(" ", "");
                st = st.replace(".", "-");
                end = end.replace(".", "-");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                LocalDate startD = LocalDate.parse(st, formatter);
                LocalDate endD = LocalDate.parse(end, formatter);
                scheduleDto = scheduleRepository.createSchedule(str[0], startD, endD);
            }else {
                String st = str[1].replace(" ", "");
                st = st.replace(".", "-");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                LocalDate startD = LocalDate.parse(st, formatter);
                scheduleDto = scheduleRepository.createSchedule(str[0], startD, startD);
            }
            if(scheduleDto != null)
                result.add(scheduleDto);
        }

        return result;
    }

    public boolean deleteSchedule(int index){
        try{
            scheduleRepository.deleteSchedule(index);
            return true;
        }catch (Exception e){
            throw new CustomException(INVALID_FORM_DATA);
        }

    }
}
