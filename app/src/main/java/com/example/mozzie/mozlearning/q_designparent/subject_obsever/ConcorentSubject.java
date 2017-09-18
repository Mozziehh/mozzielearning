package com.example.mozzie.mozlearning.q_designparent.subject_obsever;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mozzie on 17/8/5.
 */

public class ConcorentSubject extends subject {

    List<Observer> mObserver = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer);
        mObserver.add(observer);
    }

    @Override
    public void removerOberserver(Observer observer) {
        super.removerOberserver(observer);
        mObserver.remove(observer);
    }

    @Override
    public void notifysub() {
        super.notifysub();
        for (Observer ob : mObserver) {
            ob.update();
        }
    }
}
