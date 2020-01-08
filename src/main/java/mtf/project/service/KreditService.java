package mtf.project.service;

import java.util.List;

import mtf.project.model.*;

public interface KreditService{
    void addKreditModel(KreditModel kreditModel);
    void changeKreditStatus(KreditModel kreditModel);
}