package com.example.demo;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HospitalsController {

  private int MAX = 100000000;
  private final HospitalsRepository hospitalrepository;
  private final LabsRepository labsrepository;

  HospitalsController(HospitalsRepository hospitalrepository, LabsRepository labsrepository) {
    this.hospitalrepository = hospitalrepository;
    this.labsrepository = labsrepository;
  }

  // Aggregate root

  @GetMapping("/hospitals")
  List<Hospitals> all() {
    return hospitalrepository.findAll();
  }

  @PostMapping("/hospitals")
  Hospitals newHospitals(@RequestBody Hospitals newHospitals) {
    return hospitalrepository.save(newHospitals);
  }

  // Single item

  @GetMapping("/hospitals/{id}")
  String one(@PathVariable Long id) {
    Object obj = hospitalrepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Could not find Hospital " + id));
    
    String supertemp = obj.toString();
    String[] tmp = supertemp.split( " ", 4);
    double [] hCoords = new double[2];

    hCoords[0] = Double.parseDouble(tmp[1]);
    hCoords[1] = Double.parseDouble(tmp[2]);

    return solve(hCoords, id);
  }

  public double getDistance( double lat1, double lon1, double lat2, double lon2 ) {
		double PiBy180 = 0.01745329251; // Math.PI / 180
    double dist = 0.5 - Math.cos((lat2 - lat1) * PiBy180)/2 + Math.cos(lat1 * PiBy180) 
                    * Math.cos(lat2 * PiBy180) * (1 - Math.cos((lon2 - lon1) * PiBy180))/2;
		
		return 12742 * Math.asin(Math.sqrt(dist));
  }
  
  public long getTime(double dist, Timestamp[] queue, int bsz, int eta, int queuesize) {
    long time = 0;
    int count = 0;
    int i = 0;
    for(Timestamp old : queue){
      if(i == queuesize) break;
      i += 1;
      Instant instant = Instant.now();
      Timestamp now = Timestamp.from(instant);
      long milliseconds1 = old.getTime();
      long milliseconds2 = now.getTime();
      long diff = milliseconds2 - milliseconds1;
      long diffMinutes = diff / (60 * 1000);

      if(diffMinutes < eta + 300) {
        count += 1;
        if(time > (long)(eta + 300)) time = (long)(eta + 300);
      }
    }

    if(count > bsz * 10) return MAX;
    return 5*60 + (int)(2*(dist)/50*60) + time;
  }

  public String solve(double[] hCoords, Long id){
    List<Labs> labs= labsrepository.findAll();

    Labs ans = new Labs();
    long time = MAX;
    double dist = MAX;
    for(Labs l : labs) {
      double x = l.getLat(), y = l.getLon();
      double d = getDistance(hCoords[0], hCoords[1], x, y);
      if(d > 300) continue;
      long t;
      if(l.getQueueSize() == 0) t = 300 + (long)(2*d/50);
      
      else t = getTime(d, l.getTimestamps(), l.getBatchSize(), l.getETA(), l.getQueueSize());
      if(t < time){
        ans = l;
        time = t;
        dist = d;
      }
    }

    if(ans.getName() == "NULL") return "No Labs near " + hospitalrepository.findById(id);

    ans.setETA((int)dist/50);
    ans.setQueuesize(ans.getQueueSize()+1);

    Instant instant = Instant.now();
    Timestamp now = Timestamp.from(instant);
    Timestamp[] queue = ans.getTimestamps();
    queue[ans.getQueueSize()-1] = now;
    ans.setQueue(queue);
    System.out.println(now);

    return ans.toString();
  }
}