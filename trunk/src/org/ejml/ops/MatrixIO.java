/*
 * Copyright (c) 2009-2010, Peter Abeles. All Rights Reserved.
 *
 * This file is part of Efficient Java Matrix Library (EJML).
 *
 * EJML is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * EJML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with EJML.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.ejml.ops;

import org.ejml.data.DenseMatrix64F;
import org.ejml.data.Matrix64F;

import java.io.*;


/**
 * 
 *
 * @author Peter Abeles
 */
public class MatrixIO {

    /**
     * Creates a window visually showing the matrix's state.  Block means an element is zero.
     * Red positive and blue negative.  More intense the color larger the element's absolute value
     * is.
     * 
     * @param A
     */
    public static void displayMatrix( DenseMatrix64F A ) {
        // TODO implement
    }

    /**
     * Saves a matrix to disk using Java binary serialization.
     *
     * @param A The matrix being saved.
     * @param fileName Name of the file its being saved at.
     * @throws java.io.IOException
     */
    public static void save( DenseMatrix64F A , String fileName )
        throws IOException
    {
        FileOutputStream fileStream = new FileOutputStream(fileName);
        ObjectOutputStream stream= new ObjectOutputStream(fileStream);

        try {
            stream.writeObject(A);
            stream.flush();
        } finally {
            // clean up
            try {
                stream.close();
            } finally {
                fileStream.close();
            }
        }

    }

    /**
     * Loads a DeneMatrix64F which has been saved to file using Java binary
     * serialization.
     *
     * @param fileName The file being loaded.
     * @return  DenseMatrix64F
     * @throws IOException
     */
    public static DenseMatrix64F load( String fileName )
        throws IOException
    {
        FileInputStream fileStream = new FileInputStream(fileName);
        ObjectInputStream stream = new ObjectInputStream(fileStream);

        DenseMatrix64F ret;
        try {
            ret = (DenseMatrix64F)stream.readObject();
            if( stream.available() !=  0 ) {
                throw new RuntimeException("File not completely read?");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        stream.close();
        return ret;
    }

    public static void print( Matrix64F mat ) {
        print(mat,6,3);
    }

    public static void print(Matrix64F mat , int numChar , int precision ) {
        String format = "%"+numChar+"."+precision+"f ";

        print(mat,format);
    }

    public static void print(Matrix64F mat , String format ) {
        System.out.println("Type = dense , numRows = "+mat.numRows+" , numCols = "+mat.numCols);

        format += " ";

        for( int y = 0; y < mat.numRows; y++ ) {
            for( int x = 0; x < mat.numCols; x++ ) {
                System.out.printf(format,mat.get(y,x));
            }
            System.out.println();
        }
    }
}
