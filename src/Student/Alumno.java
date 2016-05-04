/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//dimension get stringsize
package Student;
/**
 *
 * @author Alumno
 */
public class Alumno {
    private String nombre;
    private int[] nota=new int[3];
    
    public Alumno(String nombre){
        this.nombre=nombre;
        for(int x=0;x<nota.length;x++){
            nota[x]=0;
        }
    }
    
    public Alumno(String nombre, int n1, int n2, int n3){
        this.nombre=nombre;
        this.nota[0]=n1;
        this.nota[1]=n2;
        this.nota[2]=n3;
    }
    
    public String[] getArrayAlumno(){
//    String nota1,nota2,nota3;
//    nota1 = nota[0];
      String[] info = {getNombre(),""};
      return null;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @param num_nota
     * @return the nota
     */
    public int getNota(int num_nota) {
        return nota[num_nota];
    }
}
