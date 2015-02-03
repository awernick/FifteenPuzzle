package controller;

/**
 *
 * Interface to handle BoardEvents such as change, and solve.d
 *
 * Created by awernick on 2/3/15.
 */
public interface BoardEventListener
{
        public void boardChanged();
        public void boardSolved();
}
