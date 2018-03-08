package dk.easv.bll.bot;

import dk.easv.bll.field.IField;
import dk.easv.bll.game.GameManager;
import dk.easv.bll.game.IGameState;
import dk.easv.bll.move.IMove;
import dk.easv.bll.move.Move;
import java.util.List;
import java.util.Random;

/**
 *
 * @author MikkelK
 */
public class MMEFPrioBot implements IBot
{

    private static final String BOTNAME = "MMEFPrioBot";
    
    // Moves {row, col} in order of preferences. {0, 0} at top-left corner
    protected int[][] preferredMoves = {
            {1, 1}, //Center
            {0, 0}, {2, 2}, {0, 2}, {2, 0},  //Corners ordered across
            {0, 1}, {2, 1}, {1, 0}, {1, 2}}; //Outer Middles ordered across
    
    @Override
    public IMove doMove(IGameState state)
    {
        Random r = new Random();
        String myID = "" + (state.getMoveNumber() % 2);
        List<IMove> validMoves = state.getField().getAvailableMoves();
        for (int i = 0; i < validMoves.size(); i++)
        {

            IMove choosenMove = validMoves.get(i);

            state.getField().getBoard()[choosenMove.getX()][choosenMove.getY()] = myID;
            boolean isWin = GameManager.isWin(state.getField().getBoard(), choosenMove, myID);
            if (isWin)
            {
                return choosenMove;
            }

            //Find macroboard to play in
            for (int[] move : preferredMoves)
            {
                if (state.getField().getMacroboard()[move[0]][move[1]].equals(IField.AVAILABLE_FIELD))
                {
                    //find move to play
                    for (int[] selectedMove : preferredMoves)
                    {
                        int x = move[0] * 3 + selectedMove[0];
                        int y = move[1] * 3 + selectedMove[1];
                        if (state.getField().getBoard()[x][y].equals(IField.EMPTY_FIELD))
                        {
                            return new Move(x, y);
                        }
                    }
                }
            }
            //undo
            state.getField().getBoard()[choosenMove.getX()][choosenMove.getY()] = IField.EMPTY_FIELD;
        }

        return validMoves.get(r.nextInt(validMoves.size()));
    }

    @Override
    public String getBotName()
    {

        return BOTNAME;
    }
}