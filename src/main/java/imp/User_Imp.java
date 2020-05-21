package imp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.*;

import dao.*;
import model.*;

/* Este controlador hará request a la API como si fuese el front-end, 
 * así se optimiza el código y no hay que reescribir más líneas que las necesarias
 * Para obtener y parsear los datos
 */

public class User_Imp implements DAO_User {

	// Se definen las variables que especificarán los datos principales de la
	// Request
	final String domain = "http://localhost:3000";
	String path = "";
	String method = "";

	/*
	 * Esta función detectará si el usuario está registrado. En caso de que lo esté,
	 * generará de golpe una instancia de User, volcando en ella toda la información
	 * sobre el usuario y sus eventos
	 */
	public User loginUser(String email, String password) {
		User user = new User();
		path = "/inicio";
		method = "POST";
		// Comienza enviando a la api la request del login
		try {

			String data = "email=" + email + "&password=" + password;

			URL url = new URL(domain.concat(path));

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod(method);

			con.setDoOutput(true);

			con.getOutputStream().write(data.getBytes("UTF-8"));

			con.getInputStream();

			Reader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			String jsonResponse = "";

			for (int c; (c = in.read()) >= 0;)

				jsonResponse = jsonResponse + ((char) c);

			con.disconnect();

			JSONObject obj = new JSONObject(jsonResponse);

			// De estar registrado, guarda la token del usuario y hace las requests
			// normalmente
			if (obj.has("token")) {

				user.setToken(obj.getString("token"));

				// Guarda los detalles del usuario pasando primero la token
				path = "/account";

				data = "token=" + user.getToken();

				url = new URL(domain.concat(path));

				con = (HttpURLConnection) url.openConnection();

				con.setRequestMethod(method);

				con.setDoOutput(true);

				con.getOutputStream().write(data.getBytes("UTF-8"));

				con.getInputStream();

				in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

				jsonResponse = "";

				for (int c; (c = in.read()) >= 0;)

					jsonResponse = jsonResponse + ((char) c);

				obj = new JSONObject(jsonResponse);

				user.setIdUser(obj.getInt("idUser"));

				user.setName(obj.getString("name"));

				user.setSurname(obj.getString("surname"));

				user.setEmail(obj.getString("email"));

				// Ahora recoge la información de los eventos del usario
				path = "/eventListCreated";

				url = new URL(domain.concat(path));

				con = (HttpURLConnection) url.openConnection();

				con.setRequestMethod(method);

				con.setDoOutput(true);

				con.getOutputStream().write(data.getBytes("UTF-8"));

				con.getInputStream();

				in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

				jsonResponse = "";
				
				for (int c; (c = in.read()) >= 0;)
					
					jsonResponse = jsonResponse + ((char) c);

				JSONArray arr = new JSONArray(jsonResponse);

				ArrayList<Event> ue = new ArrayList<Event>();

				// Ahora pasa a instanciar los eventos de dicho usuario
				for (int i = 0; i < arr.length(); i++) {

					JSONObject arrObj = new JSONObject(arr.get(i).toString());

					Event e = new Event();

					e.setIdEvent(arrObj.getInt("idEvent"));

					e.setHost(arrObj.getInt("host"));

					e.setCode(arrObj.getString("code"));

					e.setTitle(arrObj.getString("title"));

					e.setDescription(arrObj.getString("description"));

					e.setDate(arrObj.getString("date"));

					e.setLocation(arrObj.getString("location"));

					e.setStreet(arrObj.getString("street"));

					e.setPostalCode(arrObj.getString("postalCode"));

					e.setClosed(arrObj.getBoolean("closed"));

					ue.add(e);

				}

				user.setEvents(ue);

				// Y por cada evento, instancia sus respectivas invitaciones con sus detalles
				path = "/getEventInvitations";

				for (int i = 0; i < user.getEvents().size(); i++) {
					
					ArrayList<Attend> at = new ArrayList<Attend>();

					data = "token=" + user.getToken() + "&idEvent=" + user.getEvents().get(i).getIdEvent();

					url = new URL(domain.concat(path));

					con = (HttpURLConnection) url.openConnection();

					con.setRequestMethod(method);

					con.setDoOutput(true);

					con.getOutputStream().write(data.getBytes("UTF-8"));

					con.getInputStream();

					in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

					jsonResponse = "";

					for (int c; (c = in.read()) >= 0;)

						jsonResponse = jsonResponse + ((char) c);

					JSONObject objInvi = new JSONObject(jsonResponse);
					
					JSONArray arrInv = objInvi.getJSONArray("invitations");

					for (int j = 0; j < arrInv.length(); j++) {

						JSONObject invi = new JSONObject(arrInv.get(j).toString());

						Attend a = new Attend();

						a.setCode(invi.getString("code"));

						a.setConfirmed(invi.getBoolean("confirmed"));

						a.setName(invi.getString("name"));

						a.setSurname(invi.getString("surname"));

						ArrayList<Wellness> wns = new ArrayList<Wellness>();

						JSONArray arrW = invi.getJSONArray("alergenics");

						for (int k = 0; k < arrW.length(); k++) {

							Wellness w = new Wellness();

							w.setName(arrW.get(k).toString());

							w.setType(Wellness.typeWellness.Alérgenos);

							wns.add(w);

						}

						arrW = invi.getJSONArray("functionality");

						for (int k = 0; k < arrW.length(); k++) {

							Wellness w = new Wellness();

							w.setName(arrW.get(k).toString());

							w.setType(Wellness.typeWellness.Diversidad);

							wns.add(w);

						}

						Detail det = new Detail();

						det.setWellness(wns);

						a.setWellness(det);

						arrW = invi.getJSONArray("Responses");

						ArrayList<Response> resp = new ArrayList<Response>();

						for (int k = 0; k < arrW.length(); k++) {

							Response r = new Response();

							JSONObject objRes = new JSONObject(arrW.get(k));

							r.setQuestion(objRes.getString("question"));

							r.setResponse(objRes.getString("response"));

							resp.add(r);

						}

						Responses res = new Responses();

						res.setResponses(resp);

						a.setResponses(res);

						at.add(a);

					}

					Attends as = new Attends();

					as.setInvitations(at);

					user.getEvents().get(i).setInvitations(as);
					
				}

				// Finalmente se desconecta de la api y retorna el usario
				con.disconnect();

			} else {

				user = null;

			}

		} catch (Exception e) {

			user = null;

		}

		return user;
	}

}
