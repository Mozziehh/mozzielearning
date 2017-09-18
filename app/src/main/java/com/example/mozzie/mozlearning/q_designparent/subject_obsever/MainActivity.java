package com.example.mozzie.mozlearning.q_designparent.subject_obsever;

import com.example.mozzie.mozlearning.b_utils.LOGGER;

/**
 * Created by mozzie on 17/8/5.
 */

public class MainActivity {

    public static void main(String args[]){
        System.out.print("mainactivity");

        ConcorentSubject subject = new ConcorentSubject();

        subject.addObserver(new ConcoreentOberserver("a", "ready"));
        subject.addObserver(new ConcoreentOberserver("b", "ready"));
        subject.addObserver(new ConcoreentOberserver("c", "not-ready"));
        subject.addObserver(new ConcoreentOberserver("d", "ready"));

        subject.notifysub();
    }
}
