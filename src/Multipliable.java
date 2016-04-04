/**
 * Created by habospace on 27/03/16.
 */
public interface Multipliable {

    Vec3 multiplyByVector(Vec3 vec);

    TransformationMatrix multiplyByMatrix(Multipliable matrix);

    double[][] getMultipliableMatrix();

}