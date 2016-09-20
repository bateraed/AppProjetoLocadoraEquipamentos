package ed.com.br.appprojetolocadoraequipamentos.DAO;

import java.util.ArrayList;
import java.util.List;

import ed.com.br.appprojetolocadoraequipamentos.Model.Professor;

/**
 * Created by edinilson.silva on 19/09/2016.
 */
public class ProfessorDAO {

    public List<Professor> listarProfessor(){

        Professor p1 = new Professor(1, "Edi", "ba@gmail.com", "Analaise", "ADS");
        Professor p2 = new Professor(2, "Cesar", "ce@gmail.com", "Redes", "Redes de computadores");

        List<Professor> professors = new ArrayList<>();
        professors.add(p1);
        professors.add(p2);

        return professors;
    }
}
