package kz.gvs.google_drive;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import java.awt.Desktop;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.apache.tika.Tika;

public class MainFrame extends javax.swing.JFrame {

    Drive service;
    DefaultTreeModel treeModel;
    DefaultMutableTreeNode selectedNode;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree = new javax.swing.JTree();
        jProgressBar = new javax.swing.JProgressBar();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemNewDirectory = new javax.swing.JMenuItem();
        jMenuItemUploadFile = new javax.swing.JMenuItem();
        jMenuItemShare = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemAuthorize = new javax.swing.JMenuItem();
        jMenuItemUpdate = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItemDelete = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GVS03_Google_Drive");
        setResizable(false);

        jTree.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Authorize through menu bar: File -> Authorize");
        jTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree.setCellRenderer(new FileTreeCellRenderer());
        jTree.setEnabled(false);
        jTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreeMouseClicked(evt);
            }
        });
        jTree.addTreeWillExpandListener(new javax.swing.event.TreeWillExpandListener() {
            public void treeWillCollapse(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
            }
            public void treeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {
                jTreeTreeWillExpand(evt);
            }
        });
        jTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTreeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree);

        jProgressBar.setVisible(false);

        jMenuFile.setText("File");

        jMenuItemNewDirectory.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemNewDirectory.setText("New Directory...");
        jMenuItemNewDirectory.setEnabled(false);
        jMenuItemNewDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewDirectoryActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemNewDirectory);

        jMenuItemUploadFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItemUploadFile.setText("Upload File...");
        jMenuItemUploadFile.setEnabled(false);
        jMenuItemUploadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUploadFileActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemUploadFile);

        jMenuItemShare.setText("Share");
        jMenuItemShare.setEnabled(false);
        jMenuItemShare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemShareActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemShare);
        jMenuFile.add(jSeparator1);

        jMenuItemAuthorize.setText("Authorize");
        jMenuItemAuthorize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAuthorizeActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemAuthorize);

        jMenuItemUpdate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItemUpdate.setText("Update");
        jMenuItemUpdate.setEnabled(false);
        jMenuItemUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUpdateActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemUpdate);
        jMenuFile.add(jSeparator2);

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemExit);

        jMenuBar.add(jMenuFile);

        jMenuEdit.setText("Edit");

        jMenuItemDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        jMenuItemDelete.setText("Delete");
        jMenuItemDelete.setEnabled(false);
        jMenuItemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeleteActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemDelete);

        jMenuBar.add(jMenuEdit);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(616, 539));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemAuthorizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAuthorizeActionPerformed
        progressBarEnabled(true);

        SwingWorker<Drive, Void> worker = new SwingWorker<Drive, Void>() {
            @Override
            protected Drive doInBackground() throws IOException {
                return GoogleDriveUtils.getDriveService();
            }

            @Override
            protected void done() {
                progressBarEnabled(false);
                try {
                    service = get();
                    loadRootFiles();

                    jMenuItemAuthorize.setEnabled(false);
                    jMenuItemUpdate.setEnabled(true);
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        worker.execute();
    }//GEN-LAST:event_jMenuItemAuthorizeActionPerformed

    private void jMenuItemUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUpdateActionPerformed
        loadRootFiles();
    }//GEN-LAST:event_jMenuItemUpdateActionPerformed

    private void jTreeTreeWillExpand(javax.swing.event.TreeExpansionEvent evt)throws javax.swing.tree.ExpandVetoException {//GEN-FIRST:event_jTreeTreeWillExpand
        TreePath path = evt.getPath();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
        File file = (File) node.getUserObject();

        // Load children only first time.
        if (node.isLeaf()) {
            loadChildren(node, file.getId(), () -> {
            });
        }
    }//GEN-LAST:event_jTreeTreeWillExpand

    /**
     * Handles {@link javax.swing.event.TreeSelectionEvent} by assigning value
     * to the {@link selectedNode} variable.
     */
    private void jTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTreeValueChanged
        TreePath path = evt.getPath();
        selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
        if (selectedNode.getAllowsChildren()) {
            jMenuItemNewDirectory.setEnabled(true);
            jMenuItemUploadFile.setEnabled(true);
            jMenuItemShare.setEnabled(false);
            if (selectedNode.isRoot()) {
                jMenuItemDelete.setEnabled(false);
            } else {
                jMenuItemDelete.setEnabled(true);
            }
        } else {
            jMenuItemNewDirectory.setEnabled(false);
            jMenuItemUploadFile.setEnabled(false);
            jMenuItemShare.setEnabled(true);
            jMenuItemDelete.setEnabled(true);
        }
    }//GEN-LAST:event_jTreeValueChanged

    private void jMenuItemNewDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewDirectoryActionPerformed

        String name = (String) JOptionPane.showInputDialog(this, "Enter new directory name:", "New Folder", JOptionPane.PLAIN_MESSAGE, null, null, "");
        if (name != null && name.length() > 0) {
            createFolder(name);
        }

    }//GEN-LAST:event_jMenuItemNewDirectoryActionPerformed

    private void jMenuItemUploadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUploadFileActionPerformed

        final JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            try {
                String selectedFileContent = new Tika().detect(selectedFile);
                uploadFile(selectedFile, selectedFileContent);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jMenuItemUploadFileActionPerformed

    private void jMenuItemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeleteActionPerformed
        String location;
        if (selectedNode.getAllowsChildren()) {
            location = "folder";
        } else {
            location = "file";
        }
        if (JOptionPane.showConfirmDialog(
                this, "Are you sure you want to delete selected " + location + "?",
                "Delete " + location.substring(0, 1).toUpperCase() + location.substring(1).toLowerCase(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            File selectedFile = (File) selectedNode.getUserObject();
            deleteFile(selectedFile.getId());
        }

    }//GEN-LAST:event_jMenuItemDeleteActionPerformed

    private void jTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeMouseClicked
        if (evt.getClickCount() == 2) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
            if (node == null || node.getAllowsChildren()) {
                return;
            }
            File file = (File) node.getUserObject();
            java.io.File downloadsDir = new java.io.File(System.getProperty("user.home"), "Downloads");
            java.io.File location = new java.io.File(downloadsDir, file.getName());
            downloadFile(file.getId(), location);
        }
    }//GEN-LAST:event_jTreeMouseClicked

    private void jMenuItemShareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemShareActionPerformed

        if (JOptionPane.showConfirmDialog(
                this, "Are you sure you want to share selected file, so that anyone with link can read it?",
                "Share File", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            File file = (File) selectedNode.getUserObject();
            shareFile(file.getId());
        }
    }//GEN-LAST:event_jMenuItemShareActionPerformed

    /**
     * Creates root node and loads all files from Google Drive with 'root'
     * parent.
     */
    private void loadRootFiles() {
        File rootFile = new File();
        rootFile.setId("root");
        rootFile.setName("Google Drive");
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootFile);

        jTree.setShowsRootHandles(true);
        treeModel = new DefaultTreeModel(root, true);
        jTree.setModel(treeModel);

        loadChildren(root, "root", () -> {
            jTree.setSelectionRow(0);
            jTree.expandRow(0);
        });
    }

    private void shareFile(String fileId) {
        progressBarEnabled(true);

        Permission permission = new Permission()
                .setType("anyone")
                .setRole("reader");

        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws IOException {
                service.permissions().create(fileId, permission).execute();
                File file = service.files().get(fileId).setFields("webViewLink").execute();
                return file.getWebViewLink();
            }

            @Override
            protected void done() {
                progressBarEnabled(false);
                try {
                    JTextArea ta = new JTextArea(1, 1);
                    ta.setText(get());
                    ta.setEditable(false);
                    // Select text and request focus in text area, after it was
                    // added to the dialog.
                    ta.addAncestorListener(new AncestorListener() {
                        @Override
                        public void ancestorRemoved(AncestorEvent event) {
                        }

                        @Override
                        public void ancestorMoved(AncestorEvent event) {
                        }

                        @Override
                        public void ancestorAdded(AncestorEvent event) {
                            if (event.getSource() == ta) {
                                ta.selectAll();
                                ta.requestFocusInWindow();
                                Toolkit.getDefaultToolkit()
                                        .getSystemClipboard()
                                        .setContents(
                                                new StringSelection(ta.getText()),
                                                null
                                        );
                            }
                        }
                    });
                    JOptionPane.showMessageDialog(MainFrame.this, new JScrollPane(ta), "Share Link (Copied to clipboard)", JOptionPane.INFORMATION_MESSAGE);
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        worker.execute();
    }

    private void downloadFile(String fileId, java.io.File location) {
        progressBarEnabled(true);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws IOException {
                service.files()
                        .get(fileId)
                        .executeMediaAndDownloadTo(new FileOutputStream(location));
                DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode) selectedNode.getParent();
                return null;
            }

            @Override
            protected void done() {
                progressBarEnabled(false);
                try {
                    Desktop.getDesktop().open(location);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        worker.execute();
    }

    private void deleteFile(String fileId) {
        jTree.setEnabled(false);
        progressBarEnabled(true);

        SwingWorker<TreePath, Void> worker = new SwingWorker<TreePath, Void>() {
            @Override
            protected TreePath doInBackground() throws IOException {
                service.files()
                        .delete(fileId)
                        .execute();
                DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode) selectedNode.getParent();
                TreePath nodeParentPath = new TreePath(nodeParent.getPath());
                treeModel.removeNodeFromParent(selectedNode);
                return nodeParentPath;
            }

            @Override
            protected void done() {
                progressBarEnabled(false);
                jTree.setEnabled(true);
                try {
                    jTree.setSelectionPath(get());
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        worker.execute();
    }

    private void uploadFile(java.io.File file, String fileContent) {
        jTree.setEnabled(false);
        progressBarEnabled(true);

        File fileMetadata = new File();
        File parent = (File) selectedNode.getUserObject();
        fileMetadata.setName(file.getName());
        fileMetadata.setParents(Arrays.asList(parent.getId()));
        FileContent mediaContent = new FileContent(fileContent, file);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws IOException {
                File file = service.files()
                        .create(fileMetadata, mediaContent)
                        .setFields("id, name, mimeType")
                        .execute();
                treeModel.insertNodeInto(new DefaultMutableTreeNode(file, false), selectedNode, 0);
                return null;
            }

            @Override
            protected void done() {
                progressBarEnabled(false);
                jTree.setEnabled(true);
                DefaultMutableTreeNode newNode = (DefaultMutableTreeNode) selectedNode.getChildAt(0);
                TreePath newNodePath = new TreePath(newNode.getPath());
                jTree.setSelectionPath(newNodePath);
            }
        };
        worker.execute();
    }

    private void createFolder(String name) {
        jTree.setEnabled(false);
        progressBarEnabled(true);

        File selectedFile = (File) selectedNode.getUserObject();
        String parentId = selectedFile.getId();

        File metadata = new File();
        metadata.setName(name);
        metadata.setMimeType("application/vnd.google-apps.folder");
        metadata.setParents(Arrays.asList(parentId));

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws IOException {
                File file = service.files()
                        .create(metadata)
                        .setFields("id, name, mimeType")
                        .execute();
                treeModel.insertNodeInto(new DefaultMutableTreeNode(file), selectedNode, 0);
                return null;
            }

            @Override
            protected void done() {
                progressBarEnabled(false);
                jTree.setEnabled(true);
                DefaultMutableTreeNode newNode = (DefaultMutableTreeNode) selectedNode.getChildAt(0);
                TreePath newNodePath = new TreePath(newNode.getPath());
                jTree.setSelectionPath(newNodePath);
            }
        };
        worker.execute();
    }

    private void loadChildren(DefaultMutableTreeNode node, String parentId, Runnable callback) {
        jTree.setEnabled(false);
        progressBarEnabled(true);

        SwingWorker<Void, File> worker = new SwingWorker<Void, File>() {
            @Override
            protected Void doInBackground() throws IOException {
                String query = "'" + parentId + "' in parents and trashed = false";
                String pageToken = null;

                do {
                    FileList result = service.files().list()
                            .setOrderBy("name")
                            .setQ(query)
                            .setSpaces("drive")
                            .setFields("nextPageToken, files(id, name, mimeType)")
                            .setPageToken(pageToken)
                            .execute();
                    result.getFiles().forEach(file -> {
                        publish(file);
                    });
                    pageToken = result.getNextPageToken();
                } while (pageToken != null);

                return null;
            }

            @Override
            protected void process(List<File> chunks) {
                chunks.forEach(file -> {
                    if ("application/vnd.google-apps.folder".equals(file.getMimeType())) {
                        treeModel.insertNodeInto(new DefaultMutableTreeNode(file), node, 0);
                    } else {
                        treeModel.insertNodeInto(new DefaultMutableTreeNode(file, false), node, node.getChildCount());
                    }
                });
            }

            @Override
            protected void done() {
                progressBarEnabled(false);
                callback.run();
                jTree.setEnabled(true);
            }

        };
        worker.execute();
    }

    private void progressBarEnabled(boolean b) {
        jProgressBar.setVisible(b);
        jProgressBar.setIndeterminate(b);
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String args[]) throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemAuthorize;
    private javax.swing.JMenuItem jMenuItemDelete;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemNewDirectory;
    private javax.swing.JMenuItem jMenuItemShare;
    private javax.swing.JMenuItem jMenuItemUpdate;
    private javax.swing.JMenuItem jMenuItemUploadFile;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTree jTree;
    // End of variables declaration//GEN-END:variables
}
