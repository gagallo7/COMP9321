package edu.unsw.comp9321.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import edu.unsw.comp9321.exception.ServiceLocatorException;
import edu.unsw.comp9321.jdbc.HibernateSessionFactory;
import edu.unsw.comp9321.model.Cinema;
import edu.unsw.comp9321.model.CinemaSession;
import edu.unsw.comp9321.model.Movie;
import edu.unsw.comp9321.model.Review;
import edu.unsw.comp9321.model.UserLogin;

public class HibernateMovieDAO implements MovieDAO {
	static Logger logger = Logger.getLogger(HibernateMovieDAO.class.getName());
	private Session session;
	
	public HibernateMovieDAO() throws ServiceLocatorException {
		session = HibernateSessionFactory.getSession();
		logger.info("Got connection");			
	}
	
	@Override
	public void addMovie(Movie movie){
		session.beginTransaction();
		session.save(movie);
		session.getTransaction().commit();
	}
	
	public void updateMovie(Movie movie){
		session.beginTransaction();
		session.update(movie);
		session.getTransaction().commit();
	}
	
	@Override
	public Movie getMovie(long id) { 
		Movie movie = (Movie) session.load(Movie.class, id);
		if (!movie.getReleaseDate().after(Calendar.getInstance().getTime())){ //not after today
			movie.setNowShowing(1);
		}
		return movie;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getMovieList() {
		Criteria criteria = session.createCriteria(Movie.class);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getNowShowingMovies() { //session close at getComingSoonMovies
		Criteria criteria = session.createCriteria(Movie.class);
		criteria.add(Restrictions.le("releaseDate", Calendar.getInstance().getTime())); //less or equal to
		criteria.addOrder(Order.desc("rating"));
		criteria.setMaxResults(3);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getComingSoonMovies() {
		Criteria criteria = session.createCriteria(Movie.class);
		criteria.add(Restrictions.gt("releaseDate", Calendar.getInstance().getTime())); //greater then
		criteria.addOrder(Order.asc("releaseDate"));
		criteria.setMaxResults(3);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> searchMovie(String title, String genre) {
		List<Movie> movieList = new ArrayList<Movie>();
		Criteria criteria = session.createCriteria(Movie.class);
		
		if (!title.isEmpty() && !genre.isEmpty()){
			//search with AND			
		}else{
			if (!title.isEmpty())
				criteria.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE)); //case-insensitive like
			else if (!genre.isEmpty())
				criteria.add(Restrictions.ilike("genre", genre, MatchMode.ANYWHERE));	
		}		
		movieList = criteria.list();
		for (Movie m: movieList){
			if (!m.getReleaseDate().after(Calendar.getInstance().getTime())){ //not after today
				m.setNowShowing(1);
			}
		}
		return movieList;
	}
	
	@Override
	public void addReview(Review review){
		Movie movie = getMovie(review.getMovieId());
		int numRating = movie.getRatingNum() + 1;
		int sumRating = movie.getRatingSum() + review.getRating();
		int rating = sumRating / numRating;
		movie.setRating(rating);
		movie.setRatingSum(sumRating);
		movie.setRatingNum(numRating);
		updateMovie(movie);
		session.beginTransaction();
		session.save(review);
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Review> getReviewList(Long movieID){
		Criteria criteria = session.createCriteria(Review.class);
		criteria.add(Restrictions.eq("movieId", movieID));
		return criteria.list();
	}
	
	@Override
	public void addCinema(Cinema cinema) {
		session.beginTransaction();
		session.save(cinema);
		session.getTransaction().commit();
	}
	
	@Override
	public Cinema getCinema(Long id) {
		return (Cinema) session.load(Cinema.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cinema> getCinemaList() {
		Criteria criteria = session.createCriteria(Cinema.class);
		return criteria.list();
	}
	
	@Override
	public void addCinemaSession(CinemaSession cinemaSession){
		session.beginTransaction();
		session.save(cinemaSession);
		session.getTransaction().commit();
	}
	
	@Override
	public CinemaSession getCinemaSession(Long id){
		return (CinemaSession) session.load(CinemaSession.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CinemaSession> getMovieSessions(Long movieID) {
		Criteria criteria = session.createCriteria(CinemaSession.class);
		criteria.add(Restrictions.eq("movieId", movieID));
		return criteria.list();
	}
	
	@Override
	public boolean isSessionAvailable(Long cinemaSessionID, int numTickets){
		CinemaSession cinemaSession = (CinemaSession) session.load(CinemaSession.class, cinemaSessionID);
		return ((cinemaSession.getAvailableSeats() - numTickets) >= 0);
	}
	
	@Override
	public void registerUser(UserLogin user) {
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();		
	}

	public void addUserLogin (UserLogin ul){
		session.beginTransaction();
		session.save(ul);
		session.getTransaction().commit();
	}
	
	public void updateUser (UserLogin user){
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public UserLogin confirmUser(String code){
		Criteria criteria = session.createCriteria(UserLogin.class);
		criteria.add(Restrictions.eq("code", code));
		List<UserLogin> userList = criteria.list();
		UserLogin user = userList.get(0);
		user.setConfirmed(true);
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public UserLogin getUser(String username){
		Criteria criteria = session.createCriteria(UserLogin.class);
		criteria.add(Restrictions.eq("username", username));
		List<UserLogin> userList = criteria.list();
		if (userList.isEmpty())
			return null;
		else 		
			return (UserLogin) userList.get(0);
	}
	
	public boolean usernameExists (String username){
		Criteria criteria = session.createCriteria(UserLogin.class);
		criteria.add(Restrictions.eq("username", username));
		return (!criteria.list().isEmpty());
	}
	
	public void closeSession(){
		session.close();
	}


}
