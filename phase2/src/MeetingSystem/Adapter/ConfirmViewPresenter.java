package MeetingSystem.Adapter;

import MeetingSystem.Entity.Meeting;
import MeetingSystem.GUIView.ConfirmView;
import MeetingSystem.GUIView.OKCancelView;
import MeetingSystem.MeetingStatus;
import MeetingSystem.UseCase.MeetingActionManager;
import MeetingSystem.UseCase.MeetingManager;
import MeetingSystem.UseCase.MeetingModel;
import MeetingSystem.UseCase.Model;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public class ConfirmViewPresenter extends Observable implements IPresenter {
    UUID currLogInUser;
    UUID meetingID;
    MeetingStatus meetingStatus;

    // Use case
    MeetingManager meetingManager = new MeetingActionManager();
    Model meetingModel;

    // View
    OKCancelView view;


    public ConfirmViewPresenter(UUID meetingID, UUID currLogInUser, Observer observer) {
        this.meetingID = meetingID;
        this.currLogInUser = currLogInUser;

        // set Model
        meetingModel = new MeetingModel(meetingID, currLogInUser);

        // set View
        view = new ConfirmView();

        // get meeting status
        meetingStatus = meetingModel.getMeetingStatus(meetingID);

        // set observers
        addObserver(observer);
    }

    @Override
    public void performAction(String inputDateTime, String inputAddress) {
        // do nothing
    }

    @Override
    public void performAction() {
        performConfirm(currLogInUser);
    }

    private void performConfirm(UUID currLogInUser) {

        assert meetingManager.isMeetingIdExist(meetingID);
        Meeting m = meetingManager.getMeetingWithId(meetingID);

        meetingManager.confirmMeeting(m, currLogInUser);

        // notify observers
        setChanged();
        notifyObservers(meetingID);
    }

    @Override
    public Model getModel() {
        return meetingModel;
    }

    @Override
    public UUID getMeetingID() {
        return meetingID;
    }

    @Override
    public UUID getCurrLogInUser() {
        return currLogInUser;
    }

    @Override
    public List<UUID> getUsers() {
        return null;
    }

    @Override
    public void run() {
        view.setPresenter(this);
        view.open();
    }
}