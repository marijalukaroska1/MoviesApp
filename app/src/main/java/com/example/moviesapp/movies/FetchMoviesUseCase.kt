package com.example.moviesapp.movies

import androidx.paging.*
import com.example.moviesapp.Constants
import com.example.moviesapp.networking.MoviesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val DEFAULT_PAGE_INDEX = 1
private const val DEFAULT_PAGE_SIZE = 20

class FetchMoviesUseCase @Inject constructor(val moviesApi: MoviesApi) :
    PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageIndex = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = moviesApi.queryPopularMovies(
                Constants.MOVIES_DB_API_KEY,
                page = pageIndex
            )
            val movies = response.popularMovies
            val nextKey =
                if (movies.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / DEFAULT_PAGE_SIZE)
                }
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == DEFAULT_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    class MoviesRemoteDataSourceImpl(
        private val moviesApi: MoviesApi
    ) : MoviesRemoteDataSource {

        override fun getPopularMovies(): Flow<PagingData<Movie>> {
            return Pager(
                config = PagingConfig(
                    pageSize = DEFAULT_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    FetchMoviesUseCase(moviesApi)
                }
            ).flow
        }
    }
}