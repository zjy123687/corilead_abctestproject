package ext.corilead.workPackage;

import com.ptc.core.components.forms.FormProcessingStatus;
import com.ptc.core.components.forms.FormResult;
import com.ptc.core.components.forms.FormResultAction;
import com.ptc.core.components.util.FeedbackMessage;
import com.ptc.core.ui.resources.FeedbackType;
import com.ptc.netmarkets.util.beans.NmCommandBean;
import com.ptc.windchill.wp.WorkPackage;
import wt.doc.WTDocument;
import wt.fc.Persistable;
import wt.lifecycle.LifeCycleHelper;
import wt.lifecycle.LifeCycleManaged;
import wt.lifecycle.State;
import wt.method.RemoteMethodServer;
import wt.org.WTPrincipal;
import wt.part.WTPart;
import wt.session.SessionContext;
import wt.session.SessionHelper;
import wt.session.SessionServerHelper;

import java.io.IOException;

public class SubmitUtil1 {
    /**
     * ����ύ����
     *
     * @param nmcommandbean
     *            �����Ļ���
     * @return ִ�н��
     * @throws Exception
     * @throws IOException
     */
    //private static Logger logger = Logger.getLogger(SetState.class);
    public static FormResult doSetState(NmCommandBean nmcommandbean) throws Exception {
        if (!RemoteMethodServer.ServerFlag) {
            String method = "doSetState";
            String klass = SubmitUtil1.class.getName();
            Class[] types = { NmCommandBean.class };
            Object[] values = { nmcommandbean };
            try {
                return (FormResult) RemoteMethodServer.getDefault().invoke(method, klass, null, types, values);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        // ��ȡ���ύ�û�
        WTPrincipal user = SessionHelper.getPrincipal();
        SessionContext.setEffectivePrincipal(SessionHelper.manager.getAdministrator());
        FormResult formresult = new FormResult();
        formresult.setNextAction(FormResultAction.NONE);

        try {
            // ��ȡ������
            Persistable primobject = nmcommandbean.getActionOid().getWtRef().getObject();
           // logger.debug("primobject.getClass()" + primobject.getClass());
            if (!(primobject instanceof LifeCycleManaged)) {
                formresult.addFeedbackMessage(new FeedbackMessage(FeedbackType.FAILURE, SessionHelper.getLocale(), null,
                        null, new String[] { "�ö�����ִ���ύ��ˣ�" }));
            }

            String state_str = "";
            if (!(primobject instanceof LifeCycleManaged)) {
                formresult.addFeedbackMessage(new FeedbackMessage(FeedbackType.FAILURE, SessionHelper.getLocale(), null,
                        null, new String[] { "�ö�����ִ���ύ��ˣ�" }));
            } else {
                state_str = ((LifeCycleManaged) primobject).getLifeCycleState().toString();
            }

            if (!("HHFF3".equalsIgnoreCase(state_str))) {
                formresult.addFeedbackMessage(new FeedbackMessage(FeedbackType.FAILURE, SessionHelper.getLocale(), null,
                        null, new String[] { "ֻ����Ա���״̬�����ݶ���ִ���ύ��˲�����" }));
            } else if (primobject instanceof WTPart) {
                WTPart part = (WTPart) primobject;
                 WorkPackage wp = WPUtil.createPackage(part);
                 WPUtil.addPackageMembers(wp, part);

                boolean flag = SessionServerHelper.manager.setAccessEnforced(false);
                try {
                    // ��������Ϊ�ύ���״̬
                    LifeCycleHelper.service.setLifeCycleState((LifeCycleManaged) wp, State.toState("SUBNITFORREVIEW"),true);
                    // ������Ķ�������Ϊ��ǩ��״̬
                   LifeCycleHelper.service.setLifeCycleState(part,State.toState("SUBNITFORREVIEW"), true);
                   // logger.debug("����״̬��ɣ�");
                    formresult.addFeedbackMessage(new FeedbackMessage(FeedbackType.SUCCESS, SessionHelper.getLocale(),
                            null, null, new String[] { "�ύ��˳ɹ���" }));
                } finally {
                    SessionServerHelper.manager.setAccessEnforced(flag);
                }

                formresult.setStatus(FormProcessingStatus.SUCCESS);
                formresult.setNextAction(FormResultAction.REFRESH_OPENER);
                formresult.setNextAction(FormResultAction.REFRESH_CURRENT_PAGE);

            } else if (primobject instanceof WTDocument) {

                WTDocument doc = (WTDocument) primobject;
                // WorkPackage wp = WPUtil.createDocPackage(doc);
                //WPUtil.addPackageMembers(wp, doc);
                //String reviewPath = IbaUtil.getIBAStringValue(doc, "reviewPath");
                //IbaUtil.updateIbaValue("reviewPath", wp, reviewPath);
                boolean flag = SessionServerHelper.manager.setAccessEnforced(false);
                try {
                    // ��������Ϊ�ύ����״̬
                   // LifeCycleHelper.service.setLifeCycleState((LifeCycleManaged) wp, State.toState("TIJIAOSHENYUE"),true);
                    // ������Ķ�������Ϊ�ύ����״̬
                    LifeCycleHelper.service.setLifeCycleState(doc,State.toState("SUBNITFORREVIEW"), true);
                    //logger.debug("����״̬��ɣ�");
                    formresult.addFeedbackMessage(new FeedbackMessage(FeedbackType.SUCCESS, SessionHelper.getLocale(),
                            null, null, new String[] { "�ύ��˳ɹ���" }));
                } finally {
                    SessionServerHelper.manager.setAccessEnforced(flag);
                }

                formresult.setStatus(FormProcessingStatus.SUCCESS);
                formresult.setNextAction(FormResultAction.REFRESH_OPENER);
                formresult.setNextAction(FormResultAction.REFRESH_CURRENT_PAGE);

            } else {
                try {
                    LifeCycleHelper.service.setLifeCycleState((LifeCycleManaged) primobject,
                            State.toState("SUBNITFORREVIEW"), true);
                    formresult.addFeedbackMessage(new FeedbackMessage(FeedbackType.SUCCESS, SessionHelper.getLocale(),
                            null, null, new String[] { "�ύ��˳ɹ���" }));
                } catch (Exception e) {
                    e.printStackTrace();
                    formresult.addFeedbackMessage(new FeedbackMessage(FeedbackType.FAILURE, SessionHelper.getLocale(),
                            null, null, new String[] { "�ύ���ʧ�ܣ�" + e.getMessage() }));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            SessionContext.setEffectivePrincipal(user);
        }

        return formresult;
    }
}
