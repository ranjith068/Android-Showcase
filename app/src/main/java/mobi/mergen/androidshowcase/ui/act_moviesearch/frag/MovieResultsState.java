package mobi.mergen.androidshowcase.ui.act_moviesearch.frag;

import android.content.Context;
import android.widget.TextView;

import com.kennyc.view.MultiStateView;

import java.util.List;

import io.reactivex.functions.Consumer;
import mobi.mergen.androidshowcase.R;
import mobi.mergen.androidshowcase.data.Movie;
import mobi.mergen.androidshowcase.databinding.FragmentMovieSearchBinding;
import mobi.mergen.androidshowcase.ui.base.IState;

public enum MovieResultsState implements IState<FragmentMovieSearchBinding> {
    EMPTY(MovieResultsState::setEmpty),
    LOADING(MovieResultsState::setLoading),
    CONTENT(MovieResultsState::setContent),
    ERROR(MovieResultsState::setError);

    private Consumer<FragmentMovieSearchBinding> consumer;

    MovieResultsState(Consumer<FragmentMovieSearchBinding> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void decorate(FragmentMovieSearchBinding binding) {
        decorate(consumer, binding);
    }

    private static void setEmpty(FragmentMovieSearchBinding binding) {
        binding.multistateview.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    private static void setLoading(FragmentMovieSearchBinding binding) {
        binding.multistateview.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    private static void setContent(FragmentMovieSearchBinding binding) {
        binding.multistateview.setViewState(MultiStateView.VIEW_STATE_CONTENT);

        List<Movie> movies = binding.getData().getMovies();
        ((MovieResultsAdapter) binding.recyclerviewResults.getAdapter())
                .setNewData(movies);
    }

    private static void setError(FragmentMovieSearchBinding binding) {
        binding.multistateview.setViewState(MultiStateView.VIEW_STATE_ERROR);

        Context context = binding.getRoot().getContext();

        String errorMsg = context.getString(R.string.moviesearch_results_error);
        if (binding.getData() != null) {
            errorMsg = binding.getData().getErrorMsg();
        }

        ((TextView) binding.multistateview.getView(MultiStateView.VIEW_STATE_ERROR)
                .findViewById(R.id.textview_message)).setText(errorMsg);
    }
}
