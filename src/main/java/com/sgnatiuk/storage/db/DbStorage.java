package com.sgnatiuk.storage.db;

import com.sgnatiuk.Movie;
import com.sgnatiuk.storage.IStorage;
import com.sgnatiuk.storage.db.filters.DbFilter;
import com.sgnatiuk.storage.db.filters.DbFiltersFactory;
import com.sgnatiuk.storage.filters.FiltersContainer;

import java.util.Collection;
import java.util.Collections;
import java.util.StringJoiner;

/**
 * Created by sgnatiuk on 5/16/16.
 */
public class DbStorage implements IStorage<DbFilter> {

    @Override
    public void insertMovie(Movie movie) {

        System.out.println(String.format("[DEBUG] insert into movies (name, year, genres) " +
                "values (%s, %s,%s);", movie.getName(), movie.getYear(), movie.getGenres()));
    }

    @Override
    public Collection<Movie> getMoviesByFilter(FiltersContainer<DbFilter> filter) {

        String selectQuery = buildSelectQuery(filter);

        System.out.println("[DEBUG] select by query: "+selectQuery);

        return Collections.emptyList();
    }

    private String buildSelectQuery(FiltersContainer<DbFilter> filters){
        StringBuilder stringBuilder = new StringBuilder("select * from movies where ");
        stringBuilder.append(buildConditionPart(filters));
        return stringBuilder.toString();
    }

    private String buildConditionPart(FiltersContainer<DbFilter> filters){
        StringJoiner stringJoiner = new StringJoiner(" AND ");
        for (DbFilter dbFilter : filters.getFilters()) {
            stringJoiner.add(dbFilter.getQueryCondition());
        }
        return stringJoiner.toString();
    }

    @Override
    public DbFiltersFactory getFiltersFactory() {
        return new DbFiltersFactory();
    }
}
