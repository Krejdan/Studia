package test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import daos.QuestionDao;
import daos.*;
import tables.Question;
import tables.Resource;
import util.HibernateUtil;

public class InsertQuestions {

	private Resource questionResource;
	
	public InsertQuestions() {
		ResourceDao rdao = new ResourceDao();
		this.questionResource = rdao.get(1);
	}
	
	public void insertQuestion(String questionText, String answer1, String answer2, String answer3, String correctAnswer, String pathToImage, String extension) {
		Question question = new Question();
		QuestionDao qdao = new QuestionDao();
		byte[] data;
		try {
			data = Files.readAllBytes(Paths.get(this.getClass().getResource(pathToImage).toURI()));
			question.setQuestion(questionText);
			question.setAnswer1(answer1);
			question.setAnswer2(answer2);
			question.setAnswer3(answer3);
			question.setCorrectAnswer(correctAnswer);
			question.setImage(data);
			question.setExtension(extension);
			question.setResource(questionResource);
			qdao.add(question);
			
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

	}
	
	
	public void insertExampleQuestions() {
		insertQuestion("Czy w tej sytuacji wolno ci wyprzedziæ jad¹cy przed toba pojazd?", "Tak", null, null, "Nie", "/questions/1.png", "png");
		insertQuestion("Czy w tej sytuacji odleg³oœæ od znaku ostrzegawczego do miejsca niebezpiecznego wynosi do 100 metrów?", "Tak", null, null, "Nie", "/questions/2.png", "png");
		insertQuestion("Czy w tym miejscu wolno ci zmieniæ dotychczas zajmowany pas ruchu na pas przy prawej krawêdzi jezdni?", "Tak", null, null, "Nie", "/questions/3.png", "png");
		insertQuestion("Czy jad¹c z w³¹czonymi œwiat³ami mijania, w intensywnym deszczu ograniczaj¹cym widocznoœæ, masz prawo u¿ywaæ œwiate³ przeciwmgielnych przednich?", "Nie", null, null, "Tak", "/questions/4.png", "png");
		insertQuestion("Czy w przedstawionej sytuacji jesteœ ostrzegany o nierównoœciach na drodze, wystêpuj¹cych na odcinku 1,2km za znakiem?", "Nie", null, null, "Tak", "/questions/5.png", "png");
		insertQuestion("Który z wymienionych czynników mo¿e byæ przyczyna zmniejszania sie poziomu p³ynu w uk³adzie ch³odzenia?", "Praca silnika na wysokich obrotach", "Uszkodzenie paska napêdu pompy wody", null, "Nieszczelnoœæ uk³adu ch³odzenia", "/questions/6.png", "png");
		insertQuestion("Na co, przede wszystkim, powinieneœ zwróciæ uwagê podczas wymijania innego pojazdu ?", "Na prawid³owym u³o¿eniu r¹k na kierownicy", "Na pojazdy jad¹ce za tob¹ ", null, "Na zachowaniu odpowiedniego odstêpu", "/questions/7.png", "png");
		insertQuestion("Który z pokazanych wskaŸników kontrolnych po zapaleniu sygnalizuje brak ³adowania akumulatora ?", "B", "C", null, "A", "/questions/8.png", "png");
		insertQuestion("Czego mo¿emy siê spodziewaæ podczas gwa³townego hamowania w takich warunkach?", "Zwiêkszonej przyczepnoœci kó³ do jezdni", "Zwiêkszonej si³y tarcia", null, "Zmniejszonej przyczepnoœci kó³ do jezdni", "/questions/9.png", "png");
		insertQuestion("Z jak¹ maksymaln¹ dopuszczaln¹ prêdkoœci¹ mo¿esz jechaæ na drodze za tym znakiem ?", "Do 50 km/h.", "Do 30 km/h.", null, "Do 20 km/h.", "/questions/10.png", "png");
		insertQuestion("Jak¹ funkcjê spe³nia system (ABS)?", "Skraca drogê hamowania w ka¿dych warunkach ? ", "Zapobiega zerwaniu przyczepnoœci opon podczas przyœpieszania ?", null, "U³atwia omijanie przeszkód w czasie hamowania?", "/questions/11.png", "png");
		insertQuestion("Z jak¹ dopuszczaln¹ maksymaln¹ prêdkoœci¹ wolno Ci kierowaæ samochodem osobowym po drodze za tym znakiem ?", "60 km/h – przez ca³¹ dobê", "60 km/h – w godzinach 5.00 – 23.00", null, "50 km/h – w godzinach 5.00 – 23.00", "/questions/12.png", "png");
		insertQuestion("Jaka jest maksymalna dopuszczalna odleg³oœæ, licz¹c od tylnej p³aszczyzny obrysu pojazdu, na któr¹ mo¿e wystawaæ  ³adunek przewo¿ony pojazdem samochodowym?", "1 m.","0,5 m." , null, "2 m.", "/questions/13.png", "png");
		insertQuestion("Przy jakim stê¿eniu alkoholu we krwi zabronione jest kierowanie pojazdem samochodowy? ", "0,1 ‰ ", "0,05 ‰ ", null, "0,2 ‰", "/questions/14.png", "png");
		insertQuestion("Którym z wymienionych pojazdów masz prawo wjechaæ na tak oznakowan¹ drogê ? ", "Którym z wymienionych pojazdów masz prawo wjechaæ na tak oznakowan¹ drogê ? ", "Motorower, przeje¿d¿aj¹c do najbli¿szego zjazdu", null, "Ci¹gnikiem rolniczym, który wykonuje prace porz¹dkowe na tej drodze", "/questions/15.png", "png");
		insertQuestion("Czy poduszka powietrzna spe³ni swoj¹ funkcjê w czasie wypadku, je¿eli nie zapniesz pasów bezpieczeñstwa ?", "Nie, zadzia³anie poduszki powietrznej narazi Ciê na uraz g³owy lub krêgos³upa", "Tak, poduszka powietrzna zastêpuje u¿ycie pasów bezpieczeñstwa ", null, "Tak, s¹ to dwa niezale¿ne systemy bezpieczeñstwa", "/questions/16.png", "png");
		insertQuestion("Z jak¹ maksymaln¹ prêdkoœci¹ wolno Ci kierowaæ samochodem osobowym na drodze za tym znakiem zakazu ?", "50 km/h - przez ca³¹ dobê", "60 km/h - w godzienach 23.00 - 5.00", "80 km/h - w godzinach 23.00 - 5.00", "80 km/h - przez ca³¹ dobê", "/questions/19.png", "png");
		insertQuestion("W której sytacji zapiêty pas bezpieczeñstwa nie bêdzie dzia³a³ w³aœciwie ?", "Gdy jest nie skrêcony", "Gdy jego górna czêœæ jest umieszczona poni¿ej szyi", "Gdy jest napiêty", "Gdy jest skrêcony", "/questions/20.png", "png");
		insertQuestion("Jaki minimalny odstêp od pojazdu poprzedzaj¹cego powinniœmy zachowaæ, stoj¹c w tunelu, w zatorze drogowym ?", "3 m.", "7 m.", "Jak najmniejszy", "5 m.", "/questions/21.png", "png");
		insertQuestion("Czy podczas wymijania w nocy pojazdu oœwietlaj¹cego drogê swoimi œwiat³ami mo¿esz nie dostrzegaæ przeszkód na jezdni ?", "Kto to s¹ przeszkody?", "Nie, œwiat³a pojazdu z przeciwka zawsze poprawiaj¹ widocznoœæ na jezdni", "Nie, nie wp³ywa to na widocznoœæ na drodze", "Tak, ze wzglêdu na mo¿liwoœæ oœlepienia œwiat³ami", "/questions/22.png", "png");
		insertQuestion("W jaki sposób nale¿y obserwowaæ otoczenie podczas jazdy po autostradzie ?", "Patrzeæ wy³¹cznie do przodu", "Poœwiêciæ tyle samo uwagi na obserwacjê drogi przed i za pojazdem", "Poœwiêciæ wiêkszoœæ uwagi obserwacji drogi za pojazdem", "Patrzeæ do przodu i regularnie kontrolowaæ drogê za pojazdem w lusterkach wstecznych", "/questions/23.png", "png");
		insertQuestion("W jakim terminie masz obowi¹zkowo przeprowadziæ okresowe badanie techniczne samochodu osobowego ?", "Zawsze co 3 lata.", "Zawsze co 2 lata", "Zawsze co pó³ roku", "Przed up³ywem terminu podanego w dowodzie rejestracyjnym pojazdu", "/questions/24.png", "png");
		insertQuestion("Czym mo¿e byæ spowodowany nadmierny luz ko³a kierownicy samochodu ?", "Zu¿yte opony", "Niewywa¿one ko³a kierowane", "Zepsute wspomaganie kierownicy", "Zu¿ycie przegubów dr¹¿ków kierowniczych", "/questions/25.png", "png");
		insertQuestion("Czy naruszasz przepisy ruchu drogowego, je¿eli kierujesz pojazdem rozmawiasz przez telefon, trzymaj¹c go w rêce ?", "Nie", "Nie, pod warunkiem panowania nad pojazdem", "Nie, pod warunkiem, ¿e trzymasz telefon zdala od ucha z w³¹czonym trybem g³oœnomówi¹cym", "Tak", "/questions/25.png", "png");
		insertQuestion("Czym mo¿e byæ spowodowany nadmierny luz ko³a kierownicy samochodu ?", "Zu¿yte opony", "Niewywa¿one ko³a kierowane", "Zepsute wspomaganie kierownicy", "Zu¿ycie przegubów dr¹¿ków kierowniczych", "/questions/27.png", "png");
	}

	public static void main(String[] args) {
		InsertQuestions testInsert = new InsertQuestions();
		testInsert.insertExampleQuestions();
		HibernateUtil.shutdown();

	}

}
