package com.jagrosh.jmusicbot.commands.dj;


import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.audio.QueuedTrack;
import com.jagrosh.jmusicbot.commands.DJCommand;
import com.jagrosh.jmusicbot.queue.AbstractQueue;

/**
 * Command that provides users the ability to move a track in the playlist.
 */
public class MoveTrackCmd extends DJCommand
{

    public MoveTrackCmd(Bot bot)
    {
        super(bot);
        this.name = "트랙이동";
        this.help = "현재 큐의 트랙을 다른 위치로 이동시킵니다";
        this.arguments = "<from> <to>";
        this.aliases = bot.getConfig().getAliases(this.name);
        this.bePlaying = true;
    }

    @Override
    public void doCommand(CommandEvent event)
    {
        int from;
        int to;

        String[] parts = event.getArgs().split("\\s+", 2);
        if(parts.length < 2)
        {
            event.replyError("Please include two valid indexes.");
            return;
        }

        try
        {
            // Validate the args
            from = Integer.parseInt(parts[0]);
            to = Integer.parseInt(parts[1]);
        }
        catch (NumberFormatException e)
        {
            event.replyError("Please provide two valid indexes.");
            return;
        }

        if (from == to)
        {
            event.replyError("Can't move a track to the same position.");
            return;
        }

        // Validate that from and to are available
        AudioHandler handler = (AudioHandler) event.getGuild().getAudioManager().getSendingHandler();
        AbstractQueue<QueuedTrack> queue = handler.getQueue();
        if (isUnavailablePosition(queue, from))
        {
            String reply = String.format("`%d` is not a valid position in the queue!", from);
            event.replyError(reply);
            return;
        }
        if (isUnavailablePosition(queue, to))
        {
            String reply = String.format("`%d` is not a valid position in the queue!", to);
            event.replyError(reply);
            return;
        }

        // Move the track
        QueuedTrack track = queue.moveItem(from - 1, to - 1);
        String trackTitle = track.getTrack().getInfo().title;
        String reply = String.format("Moved **%s** from position `%d` to `%d`.", trackTitle, from, to);
        event.replySuccess(reply);
    }

    private static boolean isUnavailablePosition(AbstractQueue<QueuedTrack> queue, int position)
    {
        return (position < 1 || position > queue.size());
    }
}