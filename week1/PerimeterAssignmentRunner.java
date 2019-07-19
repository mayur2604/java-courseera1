import edu.duke.*;
import java.io.File;


public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int noOfPoints=0;
        for(Point t:s.getPoints())
            noOfPoints++;
        return noOfPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        int noOfPoints=getNumPoints(s);
        Point[] points=new Point[noOfPoints];
        int i=0;
        for(Point t:s.getPoints())
        {
            points[i]=t;
            i++;
        }
        double len=0;
        for(int j=0;j<noOfPoints;j++)
        {
            if(j==noOfPoints-1)
                len+=points[j].distance(points[0]);
            else
            len+=points[j].distance(points[j+1]);
        }
        return len/noOfPoints;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        int noOfPoints=getNumPoints(s);
        Point[] points=new Point[noOfPoints];
        int i=0;
        for(Point t:s.getPoints())
        {
            points[i]=t;
            i++;
        }
        double len=0;
        double max=0;
        for(int j=0;j<noOfPoints;j++)
        {
            if(j==noOfPoints-1)
                len=points[j].distance(points[0]);
            else
            len=points[j].distance(points[j+1]);
            max=Math.max(len,max);
        }
        return max;
    }


    public double getLargestX(Shape s) {
        // Put code here
        int noOfPoints=getNumPoints(s);
        Point[] points=new Point[noOfPoints];
        int i=0;
        for(Point t:s.getPoints())
        {
            points[i]=t;
            i++;
        }
        double len=0;
        double max=0;
        for(int j=0;j<noOfPoints;j++)
        {
            max=Math.max(points[j].getX(),max);
        }
        return max;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr=new DirectoryResource();
        double largestPerimeter=0;
        for(File f:dr.selectedFiles())
        {
            FileResource fr=new FileResource(f);
            Shape sh=new Shape(fr);
            largestPerimeter=Math.max(largestPerimeter,getPerimeter(sh));
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        DirectoryResource dr=new DirectoryResource();
        double largestPerimeter=0;
        for(File f:dr.selectedFiles())
        {
            FileResource fr=new FileResource(f);
            Shape sh=new Shape(fr);
            if(getPerimeter(sh)>largestPerimeter)
            {
                temp=f;
                largestPerimeter=getPerimeter(sh);
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("points= "+getNumPoints(s));
        System.out.println("Average length= "+getAverageLength(s));
        System.out.println("largest side= "+getLargestSide(s));
        System.out.println("largest x= "+getLargestX(s));
        testFileWithLargestPerimeter();
        testPerimeterMultipleFiles();
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println(getLargestPerimeterMultipleFiles());
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println(getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
