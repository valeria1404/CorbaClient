import org.omg.CORBA.*;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import beca.Beca;
import beca.BecaHelper;
import beca.Llistado;

public class ClientCorba {
	public static void main(String[] args) {
		try {
			// Initial ORB
			ORB orb = ORB.init(args, null);
			// crear el contexto del nombre del servicio
			NamingContext root = NamingContextHelper.narrow(orb.resolve_initial_references("NameServices"));
			// crear una tabla de nombres
			NameComponent[] nsNom = new NameComponent[1];
			// Inicializa el nombre del objeto distante
			nsNom[0] = new NameComponent("Beca", "");
			// Recupera la referencia del objeto a partir del nombre
			Object refObj = root.resolve(nsNom);
			Beca be = BecaHelper.narrow(refObj);
			System.out.println("Promedio de lista de SGMB" + be.getPromedioListado("SGMB"));
			Llistado[] cots = be.getListado("SGMB");
			for (Llistado cc : cots) {
				System.out.println(cc.dateListado + "----" + cc.valAction);
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
