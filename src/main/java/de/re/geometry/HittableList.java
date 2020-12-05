package de.re.geometry;

import de.re.models.Ray;

import java.util.ArrayList;
import java.util.List;

public class HittableList implements Hittable {
    public List<Hittable> hittables;

    public HittableList() {
        this.hittables = new ArrayList<>();
    }

    public HittableList(List<Hittable> hittables) {
        this.hittables = hittables;
    }

    public HittableList(Hittable hittable) {
        this.hittables = new ArrayList<>();
        this.hittables.add(hittable);
    }

    public void add(Hittable hittable) {
        hittables.add(hittable);
    }

    public void clear() {
        hittables.clear();
    }

    @Override
    public boolean hit(Ray r, float tMin, float tMax, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        float closest = tMax;

        for (Hittable hittable : hittables) {
            if (hittable.hit(r, tMin, closest, tempRec)) {
                hitAnything = true;
                closest = tempRec.t;
                rec.overwrite(tempRec);
            }
        }

        return hitAnything;
    }
}
