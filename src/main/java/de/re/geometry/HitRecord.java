package de.re.geometry;

import de.re.common.Point3;
import de.re.common.Vec3;
import de.re.materials.Material;
import de.re.models.Ray;
import de.re.utility.Vectors;

public class HitRecord {
    public Point3 point;

    public Vec3 normal;

    public Material material;

    public float t;

    public boolean frontFace;

    public HitRecord() {
        this.point = new Point3();
        this.normal = new Vec3();
        this.t = 0.0f;
        this.frontFace = false;
    }

    public void setFaceNormal(Ray r, Vec3 outwardNormal) {
        frontFace = Vectors.dot(r.direction, outwardNormal) < 0;
        normal = frontFace ? outwardNormal : Vectors.negate(outwardNormal);
    }

    public void overwrite(HitRecord rec) {
        this.point = rec.point;
        this.normal = rec.normal;
        this.material = rec.material;
        this.t = rec.t;
        this.frontFace = rec.frontFace;
    }
}
